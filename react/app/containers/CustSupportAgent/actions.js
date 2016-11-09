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
  SUBMIT_RETURN_SUCCESS,
  SUBMIT_RETURN_ERROR,

  EDIT_RETURN_REQUEST,
  COMPLETE_RETURN_REQUEST,

  GET_RETURNS,
  GET_RETURNS_ERROR,
  GET_RETURNS_SUCCESS,

  MANAGE_RETURN,
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

export function submitReturnSuccess(data) {
  return {
    type: SUBMIT_RETURN_SUCCESS,
    data,
  };
}

export function submitReturnError() {
  return {
    type: SUBMIT_RETURN_ERROR,
  };
}

export function editReturnRequest() {
  return {
    type: EDIT_RETURN_REQUEST,
  };
}

export function completeReturnRequest() {
  return {
    type: COMPLETE_RETURN_REQUEST,
  };
}

export function getReturns() {
  return {
    type: GET_RETURNS,
  };
}

export function getReturnsSuccess(data) {
  return {
    type: GET_RETURNS_SUCCESS,
    data,
  };
}

export function getReturnsError(err) {
  return {
    type: GET_RETURNS_ERROR,
    err,
  };
}

export function manageReturn(item) {
  return {
    type: MANAGE_RETURN,
    item,
  };
}
