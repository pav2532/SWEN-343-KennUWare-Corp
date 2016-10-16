import expect from 'expect';
import salesReducer from '../reducer';
import { fromJS } from 'immutable';

describe('salesReducer', () => {
  it('returns the initial state', () => {
    expect(salesReducer(undefined, {})).toEqual(fromJS({}));
  });
});
