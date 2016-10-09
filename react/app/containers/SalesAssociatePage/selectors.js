import { createSelector } from 'reselect';

/**
 * Direct selector to the salesAssociatePage state domain
 */
const selectSalesAssociatePageDomain = () => (state) => state.get('salesAssociatePage');

/**
 * Other specific selectors
 */
const selectShoppingCart = () => createSelector(
  selectSalesAssociatePageDomain(),
  (state) => state.get('shoppingCart').toJS(),
);

const selectPaymentInfo = () => createSelector(
  selectSalesAssociatePageDomain(),
  (state) => state.get('paymentInfo').toJS(),
);


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

  selectShoppingCart,
  selectPaymentInfo,
};
