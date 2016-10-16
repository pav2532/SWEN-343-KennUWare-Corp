import { createSelector } from 'reselect';

/**
 * Direct selector to the custSupportManager state domain
 */
const selectCustSupportManagerDomain = () => (state) => state.get('custSupportManager');

/**
 * Other specific selectors
 */


/**
 * Default selector used by CustSupportManager
 */

const selectCustSupportManager = () => createSelector(
  selectCustSupportManagerDomain(),
  (substate) => substate.toJS()
);

export default selectCustSupportManager;
export {
  selectCustSupportManagerDomain,
};
