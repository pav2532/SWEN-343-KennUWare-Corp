import { createSelector } from 'reselect';

// selectLocationState expects a plain JS object for the routing state
const selectLocationState = () => {
  let prevRoutingState;
  let prevRoutingStateJS;

  return (state) => {
    const routingState = state.get('route'); // or state.route

    if (!routingState.equals(prevRoutingState)) {
      prevRoutingState = routingState;
      prevRoutingStateJS = routingState.toJS();
    }

    return prevRoutingStateJS;
  };
};


const selectGlobal = () => (state) => state.get('global');

const selectAuthenticated = () => createSelector(
  selectGlobal(),
  (state) => state.get('authenticated'),
);

const selectEmployee = () => createSelector(
  selectGlobal(),
  (state) => state.get('employee').toJS(),
);

export {
  selectLocationState,

  selectGlobal,
  selectAuthenticated,
  selectEmployee,
};
