import { createSelector } from 'reselect';

/**
 * Direct selector to the ssologinPage state domain
 */
const selectSSOLoginPageDomain = () => (state) => state.get('ssologinPage');

/**
 * Other specific selectors
 */
const selectUsername = () => createSelector(
  selectSSOLoginPageDomain(),
  (state) => state.get('username')
);

const selectPassword = () => createSelector(
  selectSSOLoginPageDomain(),
  (state) => state.get('password')
);


/**
 * Default selector used by SsologinPage
 */

const selectSsologinPage = () => createSelector(
  selectSSOLoginPageDomain(),
  (substate) => substate.toJS()
);

export default selectSsologinPage;
export {
  selectSSOLoginPageDomain,
  selectUsername,
  selectPassword,
};
