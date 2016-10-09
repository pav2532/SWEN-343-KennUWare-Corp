/*
 *
 * SalesAssociatePage reducer
 *
 */

import { fromJS } from 'immutable';
import {
  ADD_TO_CART,
} from './constants';

const initialState = fromJS({
  shoppingCart: [],
});

function salesAssociatePageReducer(state = initialState, action) {
  switch (action.type) {
    case ADD_TO_CART:
      return state
        .updateIn(['shoppingCart'], (arr) => arr.push({ item: action.item, quantity: action.quantity }));
    default:
      return state;
  }
}

export default salesAssociatePageReducer;
