/**
 * Logs the user in
 */

import { take, call, put, select, fork, cancel } from 'redux-saga/effects';
import { LOCATION_CHANGE, push } from 'react-router-redux';
import { LOGIN, LOGIN_SUCCESS, LOGIN_ERROR } from './constants';
import { loginSuccess, loginError } from './actions';


import request from 'utils/request';
import { selectUsername, selectPassword } from './selectors';

import { selectAuthenticated, selectEmployee } from 'containers/App/selectors';

/**
 * Github repos request/response handler
 */
export function* login() {
  // Select username from store
  const username = yield select(selectUsername());
  const password = yield select(selectPassword());

  const requestURL = '/api/sales/login';

  console.log("Username: " + username);
  console.log("Password: " + password);

  // Call our request helper (see 'utils/request')
  // const auth = yield call(request, requestURL, {
  //   method: 'POST',
  //   body: {
  //     username,
  //     password,
  //   },
  // });

  let type = 'GeneralManager';
  if (username === 'associate') {
    type = 'Associate';
  } else if (username === 'RegionalManager') {
    type = 'RegionalManager';
  }
  console.log('type: ', type);
  const auth = {
    data: {
      authenticated: true,
      employee: {
        username,
        type,
      },
    },
  };

  if (!auth.err) {
    yield put(loginSuccess(auth.data));
  } else {
    yield put(loginError(auth.err));
  }
}

export function* completeLogin() {
  // TODO: check employee type, route to page
  console.log("Completing login");
  const employee = yield select(selectEmployee());
  console.log(employee);

  if (employee.type === 'RegionalManager' || employee.type === 'GeneralManager') {
    yield put(push('sales/manage'));
  } else if (employee.type === 'Associate') {
    yield put(push('sales/associate'));
  } else {
    console.log("Employee type error", employee);
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
