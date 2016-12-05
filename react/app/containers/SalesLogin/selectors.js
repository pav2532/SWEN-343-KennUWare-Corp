import { createSelector } from 'reselect';

/**
 * Direct selector to the salesLogin state domain
 */
const selectSalesLoginDomain = () => (state) => state.get('salesLogin');

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
};
