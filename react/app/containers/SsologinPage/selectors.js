import { createSelector } from 'reselect';

/**
 * Direct selector to the ssologinPage state domain
 */
const selectSsologinPageDomain = () => (state) => state.get('ssologinPage');

/**
 * Other specific selectors
 */


/**
 * Default selector used by SsologinPage
 */

const selectSsologinPage = () => createSelector(
  selectSsologinPageDomain(),
  (substate) => substate.toJS()
);

export default selectSsologinPage;
export {
  selectSsologinPageDomain,
};
