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

  SET_PAYMENT_INFO_NAME,
  SET_PAYMENT_INFO_CCNUMBER,
  SET_PAYMENT_INFO_EXPIRATION,

  CHECKOUT,
  CHECKOUT_SUCCESS,
  CHECKOUT_ERROR,
} from './constants';

const initialState = fromJS({
  content: 'dashboard',
  successModal: false,
  errorModal: false,
  shoppingCart: [],
  paymentInfo: {
    name: '',
    ccNumber: '',
    expiration: '',
  },
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
    case SET_PAYMENT_INFO_NAME:
      return state
        .setIn(['paymentInfo', 'name'], action.value);
    case SET_PAYMENT_INFO_CCNUMBER:
      return state
        .setIn(['paymentInfo', 'ccNumber'], action.value);
    case SET_PAYMENT_INFO_EXPIRATION:
      return state
        .setIn(['paymentInfo', 'expiration'], action.value);
    case CHECKOUT:
      return state
        .set('loading', true);
    case CHECKOUT_SUCCESS:
      return state
        .set('loading', false)
        .set('successModal', true);
    case CHECKOUT_ERROR:
      return state
        .set('loading', false)
        .set('errorModal', true);

    default:
      return state;
  }
}

export default salesReducer;
