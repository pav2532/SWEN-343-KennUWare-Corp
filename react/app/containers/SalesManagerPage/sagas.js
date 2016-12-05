import { take, call, put, select, fork, cancel } from 'redux-saga/effects';
import { LOCATION_CHANGE, push } from 'react-router-redux';
import { SIGN_OUT } from 'containers/App/constants';

import request from 'utils/request';

import {
  CHECKOUT,
  GET_REVENUE_TOTAL,
  GET_REVENUE_REGION,
  GET_REVENUE_STORE,
  GET_HIGHEST_SELLER,
} from './constants';

import {
  checkoutSuccess,
  checkoutError,

  getRevenueError,
  getRevenueSuccess,

  getRevenueRegionError,
  getRevenueRegionSuccess,

  getRevenueStoreError,
  getRevenueStoreSuccess,

  getHighestSellerSuccess,
  getHighestSellerError,
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

export function* getTotalRevenue() {

  console.log("Requesting total revenue");

  const requestURL = '/api/sales/revenue';


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

export function* getHighestSeller() {

  console.log("Requesting highest seller");

  const requestURL = '/api/sales/highestseller';


  const options = {
    method: 'get',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };

  // Call our request helper (see 'utils/request')
  const highestSeller = yield call(request, requestURL, options);

  console.log("Got highest seller");
  console.log(highestSeller);

  if (!highestSeller.err) {
    yield put(getHighestSellerSuccess(highestSeller.data));
  }
  // Put the error flow here
}

export function* getRegionRevenue() {
  console.log("Requesting region revenue");
  const employee = yield select(selectEmployee());
  const regionId = employee.regionId;

  const requestURL = `/api/sales/revenue/region/${regionId}`;

  const options = {
    method: 'get',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };

  // Call our request helper (see 'utils/request')
  const revenue = yield call(request, requestURL, options);

  console.log("Got revenue by region");
  console.log(revenue);

  if (!revenue.err) {
    yield put(getRevenueRegionSuccess(revenue.data));
  }
  // Do error flow
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

export function* revenueTotalWatcher() {
  while (yield take(GET_REVENUE_TOTAL)) {
    yield call(getTotalRevenue);
  }
}

export function* highestSellerWatcher() {
  while (yield take(GET_HIGHEST_SELLER)) {
    yield call(getHighestSeller);
  }
}

export function* getRevenueStore(){
  const requestURL = '/api/sales/revenue/stores';

  const options = {
    method: 'get',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };
  // Call our request helper (see 'utils/request')
  const revenue = yield call(request, requestURL, options);

  console.log("Got revenue by store");
  console.log(revenue);

  if (!revenue.err) {
    yield put(getRevenueStoreSuccess(revenue.data));
  }
  // Do error flow
}

export function* revenueStoreWatcher() {
  while (yield take(GET_REVENUE_STORE)) {
    yield call(getRevenueStore);
  }
}

export function* revenueRegionWatcher() {
  while (yield take(GET_REVENUE_REGION)) {
    yield call(getRegionRevenue);
  }
}

export function* getRevenueStoreData() {
  const watcher = yield fork(revenueStoreWatcher);

  yield take(LOCATION_CHANGE);
  yield cancel(watcher);
  return;
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

export function* revenueTotalData() {
  const watcher = yield fork(revenueTotalWatcher);

  yield take(LOCATION_CHANGE);
  yield cancel(watcher);
  return;
}

export function* highestSellerData() {
  const watcher = yield fork(highestSellerWatcher);

  yield take(LOCATION_CHANGE);
  yield cancel(watcher);
  return;
}

export function* revenueRegionData() {
  const watcher = yield fork(revenueRegionWatcher);

  yield take(LOCATION_CHANGE);
  yield cancel(watcher);
  return;
}

// All sagas to be loaded
export default [
  signOutData,
  checkoutData,
  revenueTotalData,
  revenueRegionData,
  getRevenueStoreData,
  getHighestSeller,
];
