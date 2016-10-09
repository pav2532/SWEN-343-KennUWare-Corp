import { take, call, put, select, fork, cancel } from 'redux-saga/effects';
import { LOCATION_CHANGE, push } from 'react-router-redux';

import {
  CHECKOUT,
} from './constants';

import {
  SIGN_OUT,
} from 'containers/App/constants';

import { selectShoppingCart, selectPaymentInfo } from './selectors';

export function* checkout() {
  const shoppingCart = yield select(selectShoppingCart());
  const paymentInfo = yield select(selectPaymentInfo());

  console.log('shoppingcart: ', shoppingCart);
  console.log('paymentInfo', paymentInfo);

  const requestURL = 'api/sales/checkout';

  // Call our request helper (see 'utils/request')
  // const auth = yield call(request, requestURL, {
  //   method: 'POST',
  //   body: {
  //     username,
  //     password,
  //   },
  // });

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
