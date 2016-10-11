/*
 *
 * CustSupportManager
 *
 */

import React from 'react';
import { connect } from 'react-redux';
import selectCustSupportManager from './selectors';
import styles from './styles.css';

export class CustSupportManager extends React.Component { // eslint-disable-line react/prefer-stateless-function
  render() {
    return (
      <div className={styles.custSupportManager}>
        Customer Support Manager
      </div>
    );
  }
}

const mapStateToProps = selectCustSupportManager();

function mapDispatchToProps(dispatch) {
  return {
    dispatch,
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(CustSupportManager);
