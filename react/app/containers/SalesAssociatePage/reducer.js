/*
 *
 * SalesAssociatePage reducer
 *
 */

import { fromJS } from 'immutable';
import {
  ADD_TO_CART,
  SET_PAYMENT_INFO_NAME,
  SET_PAYMENT_INFO_CCNUMBER,
  SET_PAYMENT_INFO_EXPIRATION,

  CHECKOUT,
  CHECKOUT_SUCCESS,
  CHECKOUT_ERROR,
} from './constants';

const initialState = fromJS({
  loading: false,
  shoppingCart: [],
  paymentInfo: {
    name: '',
    ccNumber: '',
    expiration: '',
  },
});

function salesAssociatePageReducer(state = initialState, action) {
  switch (action.type) {
    case ADD_TO_CART:
      return state
        .updateIn(['shoppingCart'], (arr) => arr.push({ item: action.item, quantity: action.quantity }));

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
        .set('loading', false);
    case CHECKOUT_ERROR:
      return state
        .set('loading', false);
    default:
      return state;
  }
}

export default salesAssociatePageReducer;
