import { take, call, put, select, fork, cancel } from 'redux-saga/effects';
import { LOCATION_CHANGE, push } from 'react-router-redux';
import { SIGN_OUT } from 'containers/App/constants';

import {
  SUBMIT_RETURN,
} from './constants';

import {
  selectReturn,
} from './selectors';

// Sign out saga
export function* signOut() {
  // redirect to login page
  // TODO: do some de-auth stuff 
  // Also clear stuff like the content route on this page from state
  yield put(push('/customer-support'));
}

export function* signOutWatcher() {
  while (yield take(SIGN_OUT)) {
    yield call(signOut);
  }
}

// Individual exports for testing
export function* signOutData() {
  const watcher = yield fork(signOutWatcher);

  yield take(LOCATION_CHANGE);
  yield cancel(watcher);
  return;
}



// Submit Return saga
export function* submitReturn() {
  // Gather the data using selectors for the return
  const newReturn = yield select(selectReturn());

  console.log("New return");
  console.log(newReturn);
  // Make the api call

  // Do some success actions
}

export function* returnWatcher() {
  while (yield take(SUBMIT_RETURN)) {
    yield call(submitReturn);
  }
}

// Individual exports for testing
export function* returnData() {
  const watcher = yield fork(returnWatcher);

  yield take(LOCATION_CHANGE);
  yield cancel(watcher);
  return;
}


// All sagas to be loaded
export default [
  signOutData,
  returnData,
];
