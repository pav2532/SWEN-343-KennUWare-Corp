/*
 *
 * Sales actions
 *
 */

import {
  GOTO_DASHBOARD,
  GOTO_ORDER_EDITOR,
  GOTO_USER_MGMT,

  ADD_TO_CART,
  REMOVE_FROM_CART,

  START_ORDER,
  CONTINUE_ORDER,

  SET_PAYMENT_INFO_NAME,
  SET_PAYMENT_INFO_CCNUMBER,
  SET_PAYMENT_INFO_EXPIRATION,

  CHECKOUT,
  CHECKOUT_SUCCESS,
  CHECKOUT_ERROR,

  GET_REVENUE_REGION,
  GET_REVENUE_REGION_ERROR,
  GET_REVENUE_REGION_SUCCESS,

  GET_REVENUE_TOTAL,
  GET_REVENUE_TOTAL_ERROR,
  GET_REVENUE_TOTAL_SUCCESS,

  GET_ITEM_CATALOG,
  GET_ITEM_CATALOG_ERROR,
  GET_ITEM_CATALOG_SUCCESS,

  ENTER_PAGE,
} from './constants';

export function enterPage() {
  return {
    type: ENTER_PAGE,
  };
}

export function goToDashboard() {
  return {
    type: GOTO_DASHBOARD,
  };
}

export function goToOrderEditor() {
  return {
    type: GOTO_ORDER_EDITOR,
  };
}

export function goToUserManagement() {
  return {
    type: GOTO_USER_MGMT,
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

export function getRevenueRegion() {
  return {
    type: GET_REVENUE_REGION,
  };
}

export function getRevenueRegionSuccess(data) {
  return {
    type: GET_REVENUE_REGION_SUCCESS,
    data,
  };
}

export function getRevenueRegionError(err) {
  return {
    type: GET_REVENUE_REGION_ERROR,
    err,
  };
}

export function getRevenue() {
  return {
    type: GET_REVENUE_TOTAL,
  };
}

export function getRevenueSuccess(data) {
  return {
    type: GET_REVENUE_TOTAL_SUCCESS,
    data,
  };
}

export function getRevenueError(err) {
  return {
    type: GET_REVENUE_TOTAL_ERROR,
    err,
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
