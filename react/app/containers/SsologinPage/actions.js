/*
 *
 * SsologinPage actions
 *
 */

import {
  UPDATE_USERNAME,
  UPDATE_PASSWORD,
  LOGIN,
  LOGIN_ERROR,
} from './constants';

export function updateUsername(username) {
  return {
    type: UPDATE_USERNAME,
    username,
  };
}

export function updatePassword(password) {
  return {
    type: UPDATE_PASSWORD,
    password,
  };
}

export function login() {
  return {
    type: LOGIN,
  };
}

export function loginError(err) {
  return {
    type: LOGIN_ERROR,
    err,
  };
}
