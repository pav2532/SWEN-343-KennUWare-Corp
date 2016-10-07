/*
 *
 * SalesLogin reducer
 *
 */

import { fromJS } from 'immutable';
import {
  LOGIN,
} from './constants';

const initialState = fromJS({
  username: '',
  password: '',
});

function salesLoginReducer(state = initialState, action) {
  switch (action.type) {
    case LOGIN:
      return state
        .set('username', action.credentials.username)
        .set('password', action.credentials.password);
    default:
      return state;
  }
}

export default salesLoginReducer;
