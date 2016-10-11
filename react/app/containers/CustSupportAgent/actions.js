/*
 *
 * CustSupportAgent actions
 *
 */

import {
  GOTO_NEW_RETURN,
  GOTO_RETURNS,
} from './constants';

export function gotoNewReturn() {
  return {
    type: GOTO_NEW_RETURN,
  };
}

export function gotoReturns() {
  return {
    type: GOTO_RETURNS,
  };
}
