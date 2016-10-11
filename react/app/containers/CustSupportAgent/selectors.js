import { createSelector } from 'reselect';

/**
 * Direct selector to the custSupportAgent state domain
 */
const selectCustSupportAgentDomain = () => (state) => state.get('custSupportAgent');

/**
 * Other specific selectors
 */


/**
 * Default selector used by CustSupportAgent
 */

const selectCustSupportAgent = () => createSelector(
  selectCustSupportAgentDomain(),
  (substate) => substate.toJS()
);

export default selectCustSupportAgent;
export {
  selectCustSupportAgentDomain,
};
