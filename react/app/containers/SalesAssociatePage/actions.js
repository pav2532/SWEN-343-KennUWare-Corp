/*
 *
 * SalesAssociatePage actions
 *
 */

import {
  ADD_TO_CART,
  REMOVE_FROM_CART,
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

