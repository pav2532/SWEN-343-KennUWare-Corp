/*
 *
 * CustSupportAgent reducer
 *
 */

import { fromJS } from 'immutable';
import {
  GOTO_NEW_RETURN,
  GOTO_RETURNS,
} from './constants';

const initialState = fromJS({
  content: 'newReturn',
});

function custSupportAgentReducer(state = initialState, action) {
  switch (action.type) {
    case GOTO_NEW_RETURN:
      return state
        .set('content', 'newReturn');
    case GOTO_RETURNS:
      return state
        .set('content', 'returns');
    default:
      return state;
  }
}

export default custSupportAgentReducer;
