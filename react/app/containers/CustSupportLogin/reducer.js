/*
 *
 * CustSupportLogin reducer
 *
 */

import { fromJS } from 'immutable';
import {
  LOGIN,
} from './constants';

const initialState = fromJS({});

function custSupportLoginReducer(state = initialState, action) {
  switch (action.type) {
    case LOGIN:
      return state;
    default:
      return state;
  }
}

export default custSupportLoginReducer;
