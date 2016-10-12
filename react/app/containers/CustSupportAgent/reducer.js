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

  SUBMIT_RETURN_SUCCESS,
  SUBMIT_RETURN_ERROR,

  EDIT_RETURN_REQUEST,
  COMPLETE_RETURN_REQUEST,

  GET_RETURNS_SUCCESS,
} from './constants';

const initialState = fromJS({
  content: 'newReturn',
  successModal: false,
  errorModal: false,
  newReturn: {
    customerName: '',
    customerAddress: '',
    reason: '',
    itemId: '',
  },
  returns: [],
});

function custSupportAgentReducer(state = initialState, action) {
  switch (action.type) {
    case COMPLETE_RETURN_REQUEST:
      return state
        .set('successModal', false)
        .set('errorModal', false)
        .setIn(['newReturn', 'customerName'], '')
        .setIn(['newReturn', 'customerAddress'], '')
        .setIn(['newReturn', 'reason'], '')
        .setIn(['newReturn', 'itemId'], '');
    case EDIT_RETURN_REQUEST:
      return state
        .set('successModal', false)
        .set('errorModal', false);
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
    case SUBMIT_RETURN_SUCCESS:
      return state
        .set('successModal', true);
    case SUBMIT_RETURN_ERROR:
      return state
        .set('errorModal', true);
    case GET_RETURNS_SUCCESS:
      return state
        .set('returns', action.data);

    default:
      return state;
  }
}

export default custSupportAgentReducer;
