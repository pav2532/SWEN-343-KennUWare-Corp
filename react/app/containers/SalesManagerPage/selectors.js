import { createSelector } from 'reselect';

/**
 * Direct selector to the sales state domain
 */
const selectSalesDomain = () => (state) => state.get('salesManagerPage');

/**
 * Other specific selectors
 */
const selectShoppingCart = () => createSelector(
  selectSalesDomain(),
  (state) => state.get('shoppingCart').toJS(),
);

const selectPaymentInfo = () => createSelector(
  selectSalesDomain(),
  (state) => state.get('paymentInfo').toJS(),
);

/**
 * Default selector used by Sales
 */
const selectSales = () => createSelector(
  selectSalesDomain(),
  (substate) => substate.toJS()
);

export default selectSales;
export {
  selectSalesDomain,
  selectShoppingCart,
  selectPaymentInfo,
};
