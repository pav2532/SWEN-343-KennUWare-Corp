/*
 *
 * CustSupportAgent
 *
 */

import React from 'react';
import { connect } from 'react-redux';
import selectCustSupportAgent from './selectors';
import styles from './styles.css';

export class CustSupportAgent extends React.Component { // eslint-disable-line react/prefer-stateless-function
  render() {
    return (
      <div className={styles.custSupportAgent}>
      </div>
    );
  }
}

const mapStateToProps = selectCustSupportAgent();

function mapDispatchToProps(dispatch) {
  return {
    dispatch,
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(CustSupportAgent);
