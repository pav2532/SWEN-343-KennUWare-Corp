/*
 *
 * SalesLogin actions
 *
 */

import {
  LOGIN,
  LOGIN_ERROR,
} from './constants';

import {
  LOGIN_SUCCESS,
} from 'containers/App/constants';

export function login() {
  return {
    type: LOGIN,
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
