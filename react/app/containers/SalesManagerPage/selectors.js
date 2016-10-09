import { createSelector } from 'reselect';

/**
 * Direct selector to the sales state domain
 */
const selectSalesDomain = () => (state) => state.get('salesManagerPage');

/**
 * Other specific selectors
 */

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
};
