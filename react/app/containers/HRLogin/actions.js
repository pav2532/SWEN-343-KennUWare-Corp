/*
 *
 * HRLogin actions
 *
 */

import {
  LOGIN,
  LOGIN_ERROR,
} from './constants';

import {
  LOGIN_SUCCESS,
} from 'containers/App/constants';

export function login(credentials) {
  return {
    type: LOGIN,
    credentials,
  };
}

export function loginSuccess(data) {
  console.log("Sending: " + LOGIN_SUCCESS);
  console.log(data);
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
