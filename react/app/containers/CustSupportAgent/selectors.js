import { createSelector } from 'reselect';

/**
 * Direct selector to the custSupportAgent state domain
 */
const selectCustSupportAgentDomain = () => (state) => state.get('custSupportAgent');

/**
 * Other specific selectors
 */
const selectReturn = () => createSelector(
  selectCustSupportAgentDomain(),
  (state) => state.get('newReturn').toJS()
);

const selectManagedReturn = () => createSelector(
  selectCustSupportAgentDomain(),
  (state) => state.get('returnItem').toJS()
);

const selectNewStatus = () => createSelector(
  selectCustSupportAgentDomain(),
  (state) => state.get('newStatus')
);

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
  selectReturn,
  selectManagedReturn,
  selectNewStatus,
};
