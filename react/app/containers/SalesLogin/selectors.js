import { createSelector } from 'reselect';

/**
 * Direct selector to the salesLogin state domain
 */
const selectSalesLoginDomain = () => (state) => state.get('salesLogin');

/**
 * Other specific selectors
 */
const selectUsername = () => createSelector(
  selectSalesLoginDomain(),
  (state) => state.get('username')
);

const selectPassword = () => createSelector(
  selectSalesLoginDomain(),
  (state) => state.get('password')
);

/**
 * Default selector used by SalesLogin
 */

const selectSalesLogin = () => createSelector(
  selectSalesLoginDomain(),
  (substate) => substate.toJS()
);

export default selectSalesLogin;
export {
  selectSalesLoginDomain,
  selectUsername,
  selectPassword,
};
