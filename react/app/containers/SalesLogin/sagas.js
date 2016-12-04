/**
 * Logs the user in
 */

import { take, call, put, select, fork, cancel } from 'redux-saga/effects';
import { LOCATION_CHANGE, push } from 'react-router-redux';
import {
  LOGIN,
  LOGIN_ERROR,

  ASSOCIATE,
  GENERALMANAGER,
  REGIONALMANAGER,
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
    window.location = 'http://127.0.0.1:3000/kennuware/sso/login?from=http://127.0.0.1:3000/sales';
  }

  const username = location.slice((location.indexOf('username=') + 9));
  const sessionID = location.slice((location.indexOf('sessionID=') + 10), location.indexOf('&username='));

  const requestURL = '/api/sales/login';

  // Call our request helper (see 'utils/request')
  const auth = yield call(request, requestURL,
    {
      method: 'post',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        username,
        sessionID,
      }),
    }
  );

  if (!auth.err) {
    console.log("Logged in successfully");
    console.log(auth.data);
    yield put(loginSuccess(auth.data));
  } else {
    yield put(loginError(auth.err));
  }
}

export function* completeLogin() {
  const employee = yield select(selectEmployee());

  if (employee.type === REGIONALMANAGER || employee.type === GENERALMANAGER) {
    yield put(push('sales/manage'));
  } else if (employee.type === ASSOCIATE) {
    yield put(push('sales/associate'));
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
