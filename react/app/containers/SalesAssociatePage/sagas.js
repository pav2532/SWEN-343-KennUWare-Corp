import { take, call, put, select, fork, cancel } from 'redux-saga/effects';
import { LOCATION_CHANGE, push } from 'react-router-redux';

import request from 'utils/request';

import cookie from 'react-cookie';

import {
  CHECKOUT,
} from './constants';

import {
  checkoutSuccess,
  checkoutError,
} from './actions';

import {
  SIGN_OUT,
} from 'containers/App/constants';

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
    custAddress: 'somewhere',
    date: 'todays date',
    requestedProducts,
  };

  const options = {
    method: 'post',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    credentials: 'same-origin',
    body: JSON.stringify(body),
  };

  // Call our request helper (see 'utils/request')
  const order = yield call(request, requestURL, options);

  console.log("Checkout error: ", order);

  if (order.data !== -1) {
    yield put(checkoutSuccess());
  } else {
    yield put(checkoutError());
  }
}

export function* signOut() {
  // redirect to login page
  cookie.remove('user', { path: '/' });
  cookie.remove('sessionID', { path: '/' });
  cookie.remove('JSESSIONID', { path: '/' });
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
export function* checkoutData() {
  const watcher = yield fork(checkoutWatcher);
  const watcher2 = yield fork(signOutWatcher);

  yield take(LOCATION_CHANGE);
  yield cancel(watcher);
  yield cancel(watcher2);

  return;
}

// All sagas to be loaded
export default [
  checkoutData,
];
