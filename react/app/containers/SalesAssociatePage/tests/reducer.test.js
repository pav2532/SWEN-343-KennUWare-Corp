import expect from 'expect';
import salesAssociatePageReducer from '../reducer';
import { fromJS } from 'immutable';

describe('salesAssociatePageReducer', () => {
  it('returns the initial state', () => {
    expect(salesAssociatePageReducer(undefined, {})).toEqual(fromJS({}));
  });
});
