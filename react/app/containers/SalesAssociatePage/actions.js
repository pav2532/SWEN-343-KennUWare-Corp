/*
 *
 * SalesAssociatePage actions
 *
 */

import {
  START_ORDER,
  CONTINUE_ORDER,

  ADD_TO_CART,
  REMOVE_FROM_CART,

  SET_PAYMENT_INFO_NAME,
  SET_PAYMENT_INFO_CCNUMBER,
  SET_PAYMENT_INFO_EXPIRATION,

  CHECKOUT,
  CHECKOUT_SUCCESS,
  CHECKOUT_ERROR,
  ENTER_PAGE,

  GET_ITEM_CATALOG,
  GET_ITEM_CATALOG_SUCCESS,
  GET_ITEM_CATALOG_ERROR,

  SET_ITEM,
} from './constants';

export function setItem(item) {
  return {
    type: SET_ITEM,
    item,
  };
}

export function enterPage() {
  return {
    type: ENTER_PAGE,
  };
}

export function addToCart(item, quantity) {
  return {
    type: ADD_TO_CART,
    item,
    quantity,
  };
}

export function removeFromCart(item) {
  return {
    type: REMOVE_FROM_CART,
    item,
  };
}

export function setPaymentInfoName(value) {
  return {
    type: SET_PAYMENT_INFO_NAME,
    value,
  };
}

export function setPaymentInfoCCNumber(value) {
  return {
    type: SET_PAYMENT_INFO_CCNUMBER,
    value,
  };
}

export function setPaymentInfoExpiration(value) {
  return {
    type: SET_PAYMENT_INFO_EXPIRATION,
    value,
  };
}

export function checkout() {
  return {
    type: CHECKOUT,
  };
}

export function newOrder() {
  return {
    type: START_ORDER,
  };
}

export function continueOrder() {
  return {
    type: CONTINUE_ORDER,
  };
}

export function checkoutSuccess() {
  return {
    type: CHECKOUT_SUCCESS,
  };
}

export function checkoutError() {
  return {
    type: CHECKOUT_ERROR,
  };
}

export function restart() {
  return {
    type: START_ORDER,
  };
}

export function getItemCatalog() {
  return {
    type: GET_ITEM_CATALOG,
  };
}

export function getItemCatalogSuccess(data) {
  return {
    type: GET_ITEM_CATALOG_SUCCESS,
    data,
  };
}

export function getItemCatalogError(err) {
  return {
    type: GET_ITEM_CATALOG_ERROR,
    err,
  };
}
