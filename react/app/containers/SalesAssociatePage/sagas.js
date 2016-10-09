import { take, call, put, select, fork, cancel } from 'redux-saga/effects';
import { LOCATION_CHANGE, push } from 'react-router-redux';

import {
  CHECKOUT,
} from './constants';

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

export function* checkoutWatcher() {
  while (yield take(CHECKOUT)) {
    yield call(checkout);
  }
}
// Individual exports for testing
export function* checkoutData() {
  const watcher = yield fork(checkoutWatcher);

  yield take(LOCATION_CHANGE);
  yield cancel(watcher);

  return;
}

// All sagas to be loaded
export default [
  checkoutData,
];
