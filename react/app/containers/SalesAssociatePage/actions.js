/*
 *
 * SalesAssociatePage actions
 *
 */

import {
  ADD_TO_CART,
  REMOVE_FROM_CART,

  SET_PAYMENT_INFO_NAME,
  SET_PAYMENT_INFO_CCNUMBER,
  SET_PAYMENT_INFO_EXPIRATION,

  CHECKOUT,
} from './constants';

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

