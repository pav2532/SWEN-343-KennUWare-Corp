import { createSelector } from 'reselect';

/**
 * Direct selector to the custSupportLogin state domain
 */
const selectCustSupportLoginDomain = () => (state) => state.get('custSupportLogin');

/**
 * Other specific selectors
 */


/**
 * Default selector used by CustSupportLogin
 */

const selectCustSupportLogin = () => createSelector(
  selectCustSupportLoginDomain(),
  (substate) => substate.toJS()
);

export default selectCustSupportLogin;
export {
  selectCustSupportLoginDomain,
};
