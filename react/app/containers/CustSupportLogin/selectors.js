import { createSelector } from 'reselect';

/**
 * Direct selector to the custSupportLogin state domain
 */
const selectCustSupportLoginDomain = () => (state) => state.get('custSupportLogin');

/**
 * Other specific selectors
 */
const selectUsername = () => createSelector(
  selectCustSupportLoginDomain(),
  (state) => state.get('username')
);

const selectPassword = () => createSelector(
  selectCustSupportLoginDomain(),
  (state) => state.get('password')
);

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
  selectUsername,
  selectPassword,
};
