import { take, call, put, select, fork, cancel } from 'redux-saga/effects';
import { LOCATION_CHANGE, push } from 'react-router-redux';

import request from 'utils/request';

import cookie from 'react-cookie';

import {
  CHECKOUT,
  ENTER_PAGE,
  GET_ITEM_CATALOG,
} from './constants';

import {
  checkoutSuccess,
  checkoutError,
  getItemCatalogSuccess,
  getItemCatalogError,
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

export function* enterPage() {
  const userCookie = cookie.select(/user/);
  const username = userCookie.user;
  const sessionCookie = cookie.select(/sessionID/);
  const sessionID = sessionCookie.sessionID;
  const employee = yield select(selectEmployee());

  if (username === undefined || sessionID === undefined || employee.type === undefined || employee.type === '') {
    yield put(push('/sales'));
  }
}

export function* enterPageWatcher() {
  while (yield take(ENTER_PAGE)) {
    yield call(enterPage);
  }
}

export function* enteringSaga() {
  const watcher = yield fork(enterPageWatcher);

  yield take(LOCATION_CHANGE);
  yield cancel(watcher);
  return;
}

export function* getItemCatalog() {
  const requestURL = '/api/sales/getAllItems';

  const options = {
    method: 'get',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    credentials: 'same-origin',
  };

  // Call our request helper (see 'utils/request')
  const catalog = yield call(request, requestURL, options);

  if (!catalog.err) {
    yield put(getItemCatalogSuccess(catalog.data));
  } else {
    yield put(getItemCatalogError(catalog.err));
  }
  // Do error flow
}

export function* getItemCatalogWatcher() {
  while (yield take(GET_ITEM_CATALOG)) {
    yield call(getItemCatalog);
  }
}

// All sagas to be loaded
export default [
  checkoutData,
  enteringSaga,
  getItemCatalog,
];
