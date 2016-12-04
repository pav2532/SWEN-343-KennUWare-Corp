/**
 * Logs the user in
 */

import { take, call, put, select, fork, cancel } from 'redux-saga/effects';
import { LOCATION_CHANGE, push } from 'react-router-redux';
import {
  LOGIN,
  LOGIN_ERROR,

  AGENT,
  MANAGER
} from './constants';

import {
  LOGIN_SUCCESS,
} from 'containers/App/constants';

import { loginSuccess, loginError } from './actions';


import request from 'utils/request';
import { selectUsername, selectPassword } from './selectors';

import { selectAuthenticated, selectEmployee } from 'containers/App/selectors';

/**
 * Github repos request/response handler
 */
export function* login() {
  // Select username from store
  const location = window.location.href;

  if (location.indexOf('username=') === -1 || location.indexOf('sessionID=') === -1) {
    window.location = 'http://127.0.0.1:3000/kennuware/sso/login?from=http://127.0.0.1:3000/customer-support';
  }

  const username = location.slice((location.indexOf('username=') + 9));
  const sessionID = location.slice((location.indexOf('sessionID=') + 10), location.indexOf('&username='));


  const requestURL = '/api/customer-support/login';

  // Call our request helper (see 'utils/request')
  const auth = yield call(request, requestURL,
    {
      method: 'post',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
      credentials: 'same-origin',
      body: JSON.stringify({
        username,
        sessionID,
      }),
    }
  );

  if (!auth.err) {
    yield put(loginSuccess(auth.data));
  } else {
    yield put(loginError(auth.err));
  }
}

export function* completeLogin() {
  const employee = yield select(selectEmployee());

  if (employee.type === MANAGER) {
    yield put(push('/customer-support/manage'));
  } else if (employee.type === AGENT) {
    yield put(push('/customer-support/agent'));
  } else {
    console.log("Employee type error: ", employee);
  }
}

/**
 * Watches for LOAD_REPOS action and calls handler
 */
export function* loginWatcher() {
  while (yield take(LOGIN)) {
    yield call(login);
  }
}

export function* successWatcher() {
  while (yield take(LOGIN_SUCCESS)) {
    yield call(completeLogin);
  }
}

/**
 * Root saga manages watcher lifecycle
 */
export function* loginData() {
  // Fork watcher so we can continue execution
  const watcher = yield fork(loginWatcher);
  const watcher2 = yield fork(successWatcher);

  // Suspend execution until location changes
  yield take(LOCATION_CHANGE);
  yield cancel(watcher);
  yield cancel(watcher2);
}

// Bootstrap sagas
export default [
  loginData,
];
