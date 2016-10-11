/*
 *
 * CustSupportAgent reducer
 *
 */

import { fromJS } from 'immutable';
import {
  GOTO_NEW_RETURN,
  GOTO_RETURNS,

  SET_RETURN_CUSTOMER_NAME,
  SET_RETURN_CUSTOMER_ADDRESS,
  SET_RETURN_REASON,
  SET_RETURN_ITEM_ID,
} from './constants';

const initialState = fromJS({
  content: 'newReturn',
  newReturn: {
    customerName: '',
    customerAddress: '',
    reason: '',
    itemId: '',
  },
});

function custSupportAgentReducer(state = initialState, action) {
  switch (action.type) {
    case GOTO_NEW_RETURN:
      return state
        .set('content', 'newReturn');
    case GOTO_RETURNS:
      return state
        .set('content', 'returns');
    case SET_RETURN_CUSTOMER_NAME:
      return state
        .setIn(['newReturn', 'customerName'], action.name);
    case SET_RETURN_CUSTOMER_ADDRESS:
      return state
        .setIn(['newReturn', 'customerAddress'], action.address);
    case SET_RETURN_REASON:
      return state
        .setIn(['newReturn', 'reason'], action.reason);
    case SET_RETURN_ITEM_ID:
      return state
        .setIn(['newReturn', 'itemId'], action.id);

    default:
      return state;
  }
}

export default custSupportAgentReducer;
