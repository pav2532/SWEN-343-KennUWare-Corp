import { take, call, put, select, fork, cancel } from 'redux-saga/effects';
import { LOCATION_CHANGE, push } from 'react-router-redux';
import { SIGN_OUT } from 'containers/App/constants';

import request from 'utils/request';

import {
  SUBMIT_RETURN,
  GET_RETURNS,
} from './constants';

import {
  selectReturn,
} from './selectors';

import {
  submitReturnSuccess,
  submitReturnError,
} from './actions';

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

  const requestURL = '/api/customer-support/requestReturn';

  // Make the api call
  const options = {
    method: 'post',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
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

  const requestURL = '/api/customer-support/revenue';


  const options = {
    method: 'get',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };

  // Call our request helper (see 'utils/request')
  const revenue = yield call(request, requestURL, options);

  console.log("Got revenue");
  console.log(revenue);

  if (!revenue.err) {
    yield put(getRevenueSuccess(revenue.data));
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

// Individual exports for testing
export function* returnData() {
  const watcher = yield fork(returnWatcher);

  yield take(LOCATION_CHANGE);
  yield cancel(watcher);
  return;
}

export function* getReturnData() {
  const watcher = yield fork(getReturnsWatcher);

  yield take(LOCATION_CHANGE);
  yield cancel(watcher);
  return;
}


// All sagas to be loaded
export default [
  signOutData,
  returnData,
  getReturnData,
];
