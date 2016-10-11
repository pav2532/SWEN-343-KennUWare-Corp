import { take, call, put, select, fork, cancel } from 'redux-saga/effects';
import { LOCATION_CHANGE, push } from 'react-router-redux';

import request from 'utils/request';

import {
  CHECKOUT,
} from './constants';

import {
  SIGN_OUT,
} from 'containers/App/constants';

import { selectShoppingCart, selectPaymentInfo } from './selectors';

import { selectEmployee } from 'containers/App/selectors';

export function* checkout() {
  const shoppingCart = yield select(selectShoppingCart());
  const paymentInfo = yield select(selectPaymentInfo());
  const employee = yield select(selectEmployee());

  console.log('shoppingcart: ', shoppingCart);
  console.log('paymentInfo', paymentInfo);

  const requestURL = '/api/sales/order';

  const requestedProducts = [];
  for (let i = 0; i < shoppingCart.length; i += 1) {
    requestedProducts.push({ itemID: shoppingCart[i].item.id, quantity: shoppingCart[i].quantity });
  }

  console.log("Requested: ");
  console.log(requestedProducts);

  const body = {
    employeeID: employee.id,
    custName: paymentInfo.name,
    creditCardNumber: paymentInfo.ccNumber,
    expirationDate: paymentInfo.expiration,
    bulkDiscount: 1,
    requestedProducts,
  };

  console.log("Body: " + JSON.stringify(body));

  const options = {
    method: 'post',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(body),
  };

  console.log("About to call this api order");
  // Call our request helper (see 'utils/request')
  const order = yield call(request, requestURL, options);

  console.log("Called order");
  console.log(order);

  // TODO: success and error flow for checking out
}

export function* signOut() {
  // redirect to login page
  // TODO: do some de-auth stuff here
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
