/*
 *
 * SSOLoginPage reducer
 *
 */

import { fromJS } from 'immutable';
import {
  UPDATE_USERNAME,
  UPDATE_PASSWORD,
  LOGIN_ERROR,
} from './constants';

const initialState = fromJS({
  username: '',
  password: '',
  error: '',
});

function ssologinPageReducer(state = initialState, action) {
  switch (action.type) {
    case UPDATE_USERNAME:
      return state
        .set('username', action.username);
    case UPDATE_PASSWORD:
      return state
        .set('password', action.password);
    case LOGIN_ERROR:
      return state
        .set('username', '')
        .set('password', '')
        .set('error', `Failed Login: ${action.err}`);
    default:
      return state;
  }
}

export default ssologinPageReducer;
