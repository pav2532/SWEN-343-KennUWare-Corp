import { createSelector } from 'reselect';

/**
 * Direct selector to the salesAssociatePage state domain
 */
const selectSalesAssociatePageDomain = () => (state) => state.get('salesAssociatePage');

/**
 * Other specific selectors
 */


/**
 * Default selector used by SalesAssociatePage
 */

const selectSalesAssociatePage = () => createSelector(
  selectSalesAssociatePageDomain(),
  (substate) => substate.toJS()
);

export default selectSalesAssociatePage;
export {
  selectSalesAssociatePageDomain,
};
