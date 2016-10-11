/*
 *
 * CustSupportLogin actions
 *
 */

import {
  LOGIN,
  LOGIN_SUCCESS,
  LOGIN_ERROR,
} from './constants';

export function login(credentials) {
  return {
    type: LOGIN,
    credentials,
  };
}

export function loginSuccess(data) {
  return {
    type: LOGIN_SUCCESS,
    data,
  };
}

export function loginError(err) {
  return {
    type: LOGIN_ERROR,
    err,
  };
}
