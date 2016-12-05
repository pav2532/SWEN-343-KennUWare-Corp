/**
 * Logs the user in
 */

import { take, call, put, select, fork, cancel } from 'redux-saga/effects';
import { LOCATION_CHANGE, replace, push } from 'react-router-redux';
import {
  LOGIN,
} from './constants';

import { loginError } from './actions';

import request from 'utils/request';
import { selectUsername, selectPassword } from './selectors';

import cookie from 'react-cookie';

/**
 * Github repos request/response handler
 */
export function* login() {
  // Select username from store
  const username = yield select(selectUsername());
  const password = yield select(selectPassword());

  let location = window.location.href;

  location = location.slice((location.indexOf('from=') + 5));

  const fromURL = location;

  const requestURL = '/api/sso/login';

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
        password,
        fromURL,
      }),
    }
  );

  if (!auth.err) {
    // Should do a redirect
    const newLocation = auth.data;
    const user = newLocation.slice((newLocation.indexOf('username=') + 9));
    cookie.save('user', user, { path: '/' });
    const sessionID = newLocation.slice((newLocation.indexOf('sessionID=') + 10), newLocation.indexOf('&username='));
    cookie.save('sessionID', sessionID, { path: '/' });
    window.location = newLocation;
  } else {
    yield put(loginError(auth.err.message));
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

/**
 * Root saga manages watcher lifecycle
 */
export function* loginData() {
  // Fork watcher so we can continue execution
  const watcher = yield fork(loginWatcher);

  // Suspend execution until location changes
  yield take(LOCATION_CHANGE);
  yield cancel(watcher);
}

// Bootstrap sagas
export default [
  loginData,
];
