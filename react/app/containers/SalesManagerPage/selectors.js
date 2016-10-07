import { createSelector } from 'reselect';

/**
 * Direct selector to the sales state domain
 */
const selectSalesDomain = () => (state) => state.get('salesManagerPage');

/**
 * Other specific selectors
 */
const selectAuthenticated = () => createSelector(
  selectSalesDomain(),
  (state) => state.get('authenticated')
);

const selectEmployee = () => createSelector(
  selectSalesDomain(),
  (state) => state.get('employee')
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
  selectAuthenticated,
  selectEmployee,
};
