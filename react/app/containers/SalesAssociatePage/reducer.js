/*
 *
 * SalesAssociatePage reducer
 *
 */

import { fromJS } from 'immutable';
import {
  START_ORDER,
  CONTINUE_ORDER,

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
  successModal: false,
  shoppingCart: [],
  paymentInfo: {
    name: '',
    ccNumber: '',
    expiration: '',
  },
});

function salesAssociatePageReducer(state = initialState, action) {
  switch (action.type) {
    case START_ORDER:
      return state
        .set('successModal', false)
        .set('errorModal', false)
        .set('shoppingCart', [])
        .setIn(['paymentInfo', 'name'], '')
        .setIn(['paymentInfo', 'ccNumber'], '')
        .setIn(['paymentInfo', 'expiration'], '');
    case CONTINUE_ORDER:
      return state
        .set('successModal', false)
        .set('errorModal', false);
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

export default salesAssociatePageReducer;
