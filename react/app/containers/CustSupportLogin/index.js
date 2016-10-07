/*
 *
 * CustSupportLogin
 *
 */

import React from 'react';
import { connect } from 'react-redux';
import selectCustSupportLogin from './selectors';
import styles from './styles.css';

export class CustSupportLogin extends React.Component { // eslint-disable-line react/prefer-stateless-function
  render() {
    return (
      <div className={styles.custSupportLogin}>
      </div>
    );
  }
}

const mapStateToProps = selectCustSupportLogin();

function mapDispatchToProps(dispatch) {
  return {
    dispatch,
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(CustSupportLogin);
