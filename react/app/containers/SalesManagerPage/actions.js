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
