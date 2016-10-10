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

  ADD_TO_CART,
  REMOVE_FROM_CART,
} from './constants';

const initialState = fromJS({
  content: 'dashboard',
  shoppingCart: [],
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
    case ADD_TO_CART:
      return state
        .updateIn(['shoppingCart'], (arr) => arr.push({ item: action.item, quantity: action.quantity }));
    case REMOVE_FROM_CART:
      return state;

    default:
      return state;
  }
}

export default salesReducer;
