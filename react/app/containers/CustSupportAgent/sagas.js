import { take, call, put, select, fork, cancel } from 'redux-saga/effects';
import { LOCATION_CHANGE, push } from 'react-router-redux';
import { SIGN_OUT } from 'containers/App/constants';

import request from 'utils/request';

import cookie from 'react-cookie';

import {
  SUBMIT_RETURN,
  GET_RETURNS,
  SET_RETURN_STATUS,
  RESOLVE_RETURN,
  SET_RETURN_STATUS_SUCCESS,
  RESOLVE_RETURN_SUCCESS,
} from './constants';

import {
  selectReturn,
  selectManagedReturn,
  selectNewStatus,
} from './selectors';

import {
  submitReturnSuccess,
  submitReturnError,

  setRequestStatusError,
  setRequestStatusSuccess,
  resolveReturnError,
  resolveReturnSuccess,

  getReturnsSuccess,
} from './actions';

// Sign out saga
export function* signOut() {
  // redirect to login page
  // TODO: do some de-auth stuff 
  // Also clear stuff like the content route on this page from state
  cookie.remove('user', { path: '/' });
  cookie.remove('sessionID', { path: '/' });
  cookie.remove('JSESSIONID', { path: '/' });
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

  const requestURL = '/api/customer-support/requestReturn';

  // Make the api call
  const options = {
    method: 'post',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    credentials: 'same-origin',
    body: JSON.stringify(newReturn),
  };

  // Call our request helper (see 'utils/request')
  const returnRequest = yield call(request, requestURL, options);

  console.log("Return api result");
  console.log(returnRequest);

  if (!returnRequest.err) {
    yield put(submitReturnSuccess(returnRequest.data));
  } else {
    yield put(submitReturnError());
  }

  // Do some success actions
}

export function* getReturns() {
  console.log("Requesting total revenue");

  const requestURL = '/api/customer-support/getReturns';


  const options = {
    method: 'get',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    credentials: 'same-origin',
  };

  // Call our request helper (see 'utils/request')
  const returns = yield call(request, requestURL, options);

  console.log("Got returns");
  console.log(returns);

  if (!returns.err) {
    yield put(getReturnsSuccess(returns.data));
  }
  // Put the error flow here
}

export function* returnWatcher() {
  while (yield take(SUBMIT_RETURN)) {
    yield call(submitReturn);
  }
}

export function* getReturnsWatcher() {
  while (yield take(GET_RETURNS)) {
    yield call(getReturns);
  }
}

export function* getReturnsWatcher2() {
  while (yield take(SET_RETURN_STATUS_SUCCESS)) {
    yield call(getReturns);
  }
}

export function* getReturnsWatcher3() {
  while (yield take(RESOLVE_RETURN_SUCCESS)) {
    yield call(getReturns);
  }
}

// Individual exports for testing
export function* returnData() {
  const watcher = yield fork(returnWatcher);

  yield take(LOCATION_CHANGE);
  yield cancel(watcher);
  return;
}

export function* getReturnData() {
  const watcher = yield fork(getReturnsWatcher);
  const watcher2 = yield fork(getReturnsWatcher2);
  const watcher3 = yield fork(getReturnsWatcher3);

  yield take(LOCATION_CHANGE);
  yield cancel(watcher);
  yield cancel(watcher2);
  yield cancel(watcher3);
  return;
}


export function* setStatus() {
  // Fill in
  const managedReturn = yield select(selectManagedReturn());
  const newStatus = yield select(selectNewStatus());

  console.log('set status saga', managedReturn, newStatus);
  const requestURL = '/api/customer-support/requestStatus';

  // Make the api call
  const options = {
    method: 'post',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    credentials: 'same-origin',
    body: JSON.stringify({ returnID: managedReturn.id, status: newStatus }),
  };

  console.log("options", options);

  // Call our request helper (see 'utils/request')
  const returnRequest = yield call(request, requestURL, options);

  console.log("Return api result");
  console.log(returnRequest);

  if (!returnRequest.err) {
    yield put(setRequestStatusSuccess(returnRequest.data));
  } else {
    yield put(setRequestStatusError());
  }
}

export function* setStatusWatcher() {
  while (yield take(SET_RETURN_STATUS)) {
    yield call(setStatus);
  }
}

export function* setStatusData() {
  const watcher = yield fork(setStatusWatcher);

  yield take(LOCATION_CHANGE);
  yield cancel(watcher);
  return;
}

export function* resolveReturn() {
  // Fill in
  const managedReturn = yield select(selectManagedReturn());

  console.log('resolve saga', managedReturn);
  const requestURL = '/api/customer-support/resolve';

  // Make the api call
  const options = {
    method: 'post',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    credentials: 'same-origin',
    body: JSON.stringify({ returnID: managedReturn.id, itemID: managedReturn.itemID }),
  };

  // Call our request helper (see 'utils/request')
  const returnRequest = yield call(request, requestURL, options);

  console.log("Return api result");
  console.log(returnRequest);

  if (!returnRequest.err) {
    yield put(resolveReturnSuccess(returnRequest.data));
  } else {
    yield put(resolveReturnError());
  }
}

export function* resolveReturnWatcher() {
  while (yield take(RESOLVE_RETURN)) {
    yield call(resolveReturn);
  }
}

export function* resolveData() {
  const watcher = yield fork(resolveReturnWatcher);

  yield take(LOCATION_CHANGE);
  yield cancel(watcher);
  return;
}


// All sagas to be loaded
export default [
  signOutData,
  returnData,
  getReturnData,
  setStatusData,
  resolveData,
];
