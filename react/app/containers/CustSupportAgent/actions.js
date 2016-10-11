/*
 *
 * CustSupportAgent actions
 *
 */

import {
  GOTO_NEW_RETURN,
  GOTO_RETURNS,

  SET_RETURN_CUSTOMER_NAME,
  SET_RETURN_CUSTOMER_ADDRESS,
  SET_RETURN_REASON,
  SET_RETURN_ITEM_ID,

  SUBMIT_RETURN,
} from './constants';

export function gotoNewReturn() {
  return {
    type: GOTO_NEW_RETURN,
  };
}

export function gotoReturns() {
  return {
    type: GOTO_RETURNS,
  };
}

export function setCustomerName(name) {
  return {
    type: SET_RETURN_CUSTOMER_NAME,
    name,
  };
}

export function setCustomerAddress(address) {
  return {
    type: SET_RETURN_CUSTOMER_ADDRESS,
    address,
  };
}

export function setReturnReason(reason) {
  return {
    type: SET_RETURN_REASON,
    reason,
  };
}

export function setReturnItemId(id) {
  return {
    type: SET_RETURN_ITEM_ID,
    id,
  };
}

export function submitReturn() {
  return {
    type: SUBMIT_RETURN,
  };
}
