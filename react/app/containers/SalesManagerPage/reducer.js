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

  START_ORDER,
  CONTINUE_ORDER,

  SET_PAYMENT_INFO_NAME,
  SET_PAYMENT_INFO_CCNUMBER,
  SET_PAYMENT_INFO_EXPIRATION,

  CHECKOUT,
  CHECKOUT_SUCCESS,
  CHECKOUT_ERROR,

  GET_REVENUE_TOTAL_SUCCESS,
  GET_REVENUE_REGION_SUCCESS,

  SET_ITEM,

  GET_ITEM_CATALOG_SUCCESS,
  GET_ITEM_CATALOG_ERROR,
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
  orderItem: {
    name: '',
    id: '',
    unitPrice: '',
  },
  revenue: {
    total: '0.0',
    region: '0.0',
  },
  itemCatalog: [],
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
    case GET_REVENUE_TOTAL_SUCCESS:
      return state
        .setIn(['revenue', 'total'], action.data.revenue);
    case GET_REVENUE_REGION_SUCCESS:
      return state
        .setIn(['revenue', 'region'], action.data.revenue);
    case GET_ITEM_CATALOG_SUCCESS:
      return state
        .set('itemCatalog', action.data);
    case SET_ITEM:
      return state
        .set('orderItem', action.item);

    default:
      return state;
  }
}

export default salesReducer;
