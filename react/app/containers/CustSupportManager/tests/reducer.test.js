import expect from 'expect';
import custSupportManagerReducer from '../reducer';
import { fromJS } from 'immutable';

describe('custSupportManagerReducer', () => {
  it('returns the initial state', () => {
    expect(custSupportManagerReducer(undefined, {})).toEqual(fromJS({}));
  });
});
