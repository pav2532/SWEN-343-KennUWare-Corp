import { take, call, put, select, fork, cancel } from 'redux-saga/effects';
import { LOCATION_CHANGE, push } from 'react-router-redux';
import { SIGN_OUT } from 'containers/App/constants';

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

// All sagas to be loaded
export default [
  signOutData,
];
