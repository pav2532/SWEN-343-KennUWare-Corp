/*
 * AppReducer
 *
 * The reducer takes care of our data. Using actions, we can change our
 * application state.
 * To add a new action, add it to the switch statement in the reducer function
 *
 * Example:
 * case YOUR_ACTION_CONSTANT:
 *   return state.set('yourStateVariable', true);
 */

import {
  LOGIN_SUCCESS,
} from 'containers/SalesLogin/constants';
import { fromJS } from 'immutable';

// The initial state of the App
const initialState = fromJS({
  loading: false,
  error: false,

  authenticated: true,
  employee: {
    username: '',
    type: '',
    id: '',
  },
});

function appReducer(state = initialState, action) {
  switch (action.type) {
    case LOGIN_SUCCESS:
      return state
        .set('authenticated', true)
        .setIn(['employee', 'username'], action.data.username)
        .setIn(['employee', 'type'], action.data.type)
        .setIn(['employee', 'id'], action.data.id);
    default:
      return state;
  }
}

export default appReducer;
