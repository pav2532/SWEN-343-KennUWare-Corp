/*
 *
 * Sales reducer
 *
 */

import { fromJS } from 'immutable';
import {
  GOTO_DASHBOARD,
  GOTO_ORDER_EDITOR,
  GOTO_USER_MGMT,
} from './constants';

const initialState = fromJS({
  content: 'dashboard',
});

function salesReducer(state = initialState, action) {
  switch (action.type) {
    case GOTO_DASHBOARD:
      return state
        .set('content', 'dashboard');
    case GOTO_ORDER_EDITOR:
      return state
        .set('content', 'orderEditor');
    case GOTO_USER_MGMT:
      return state
        .set('content', 'userManagement');

    default:
      return state;
  }
}

export default salesReducer;
