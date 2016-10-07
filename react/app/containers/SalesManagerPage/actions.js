/*
 *
 * Sales actions
 *
 */

import {
  GOTO_DASHBOARD,
  GOTO_ORDER_EDITOR,
  GOTO_USER_MGMT,
} from './constants';

export function goToDashboard() {
  return {
    type: GOTO_DASHBOARD,
  };
}

export function goToOrderEditor() {
  return {
    type: GOTO_ORDER_EDITOR,
  };
}

export function goToUserManagement() {
  return {
    type: GOTO_USER_MGMT,
  };
}
