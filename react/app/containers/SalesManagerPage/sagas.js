import { take, call, put, select, fork, cancel } from 'redux-saga/effects';
import { LOCATION_CHANGE, push } from 'react-router-redux';
import { SIGN_OUT } from 'containers/App/constants';

import request from 'utils/request';

import {
  CHECKOUT,
} from './constants';

import {
  checkoutSuccess,
  checkoutError,
} from './actions';

import { selectShoppingCart, selectPaymentInfo } from './selectors';

import { selectEmployee } from 'containers/App/selectors';

export function* checkout() {
  const shoppingCart = yield select(selectShoppingCart());
  const paymentInfo = yield select(selectPaymentInfo());
  const employee = yield select(selectEmployee());

  const requestURL = '/api/sales/order';

  const requestedProducts = [];
  for (let i = 0; i < shoppingCart.length; i += 1) {
    requestedProducts.push({ itemID: shoppingCart[i].item.id, quantity: shoppingCart[i].quantity });
  }

  // The request body
  const body = {
    employeeID: employee.id,
    custName: paymentInfo.name,
    creditCardNumber: paymentInfo.ccNumber,
    expirationDate: paymentInfo.expiration,
    bulkDiscount: 1,
    requestedProducts,
  };

  const options = {
    method: 'post',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(body),
  };

  // Call our request helper (see 'utils/request')
  const order = yield call(request, requestURL, options);

  if (order.data !== -1) {
    yield put(checkoutSuccess());
  } else {
    yield put(checkoutError());
  }
}

export function* signOut() {
  // redirect to login page
  // TODO: do some de-auth stuff 
  // Also clear stuff like the content route on this page from state
  yield put(push('/sales'));
}

export function* checkoutWatcher() {
  while (yield take(CHECKOUT)) {
    yield call(checkout);
  }
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

export function* checkoutData() {
  const watcher = yield fork(checkoutWatcher);

  yield take(LOCATION_CHANGE);
  yield cancel(watcher);
  return;
}

// All sagas to be loaded
export default [
  signOutData,
  checkoutData,
];
