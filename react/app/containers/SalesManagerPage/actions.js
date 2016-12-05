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

  GET_REVENUE_STORE,
  GET_REVENUE_STORE_ERROR,
  GET_REVENUE_STORE_SUCCESS,

  GET_HIGHEST_SELLER,
  GET_HIGHEST_SELLER_ERROR,
  GET_HIGHEST_SELLER_SUCCESS,
} from './constants';

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

export function getRevenueStore() {
  return {
    type: GET_REVENUE_STORE,
  };
}

export function getRevenueStoreSuccess(data) {
  return {
    type: GET_REVENUE_STORE_SUCCESS,
    data,
  };
}

export function getRevenueStoreError(err) {
  return {
    type: GET_REVENUE_STORE_ERROR,
    err,
  };
}

export function getHighestSeller() {
  return {
    type: GET_HIGHEST_SELLER,
  };
}

export function getHighestSellerSuccess() {
  return {
    type: GET_HIGHEST_SELLER_SUCCESS,
  };
}

export function getHighestSellerError() {
  return {
    type: GET_HIGHEST_SELLER_ERROR,
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
