import expect from 'expect';
import salesLoginReducer from '../reducer';
import { fromJS } from 'immutable';

describe('salesLoginReducer', () => {
  it('returns the initial state', () => {
    expect(salesLoginReducer(undefined, {})).toEqual(fromJS({}));
  });
});
