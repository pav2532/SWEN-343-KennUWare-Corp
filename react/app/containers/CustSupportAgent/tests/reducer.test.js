import expect from 'expect';
import custSupportAgentReducer from '../reducer';
import { fromJS } from 'immutable';

describe('custSupportAgentReducer', () => {
  it('returns the initial state', () => {
    expect(custSupportAgentReducer(undefined, {})).toEqual(fromJS({}));
  });
});
