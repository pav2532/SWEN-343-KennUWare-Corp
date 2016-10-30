import expect from 'expect';
import custSupportLoginReducer from '../reducer';
import { fromJS } from 'immutable';

describe('custSupportLoginReducer', () => {
  it('returns the initial state', () => {
    expect(custSupportLoginReducer(undefined, {})).toEqual(fromJS({}));
  });
});
