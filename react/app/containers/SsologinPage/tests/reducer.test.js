import expect from 'expect';
import ssologinPageReducer from '../reducer';
import { fromJS } from 'immutable';

describe('ssologinPageReducer', () => {
  it('returns the initial state', () => {
    expect(ssologinPageReducer(undefined, {})).toEqual(fromJS({}));
  });
});
