/*
 *
 * SalesLogin
 *
 */

import React from 'react';
import { connect } from 'react-redux';
import selectSalesLogin from './selectors';
import styles from './styles.css';

import {
  login,
} from './actions';

export class SalesLogin extends React.Component { // eslint-disable-line react/prefer-stateless-function
  componentDidMount() {
    this.props.onLogin();
  }

  render() {
    return (
      <div className={styles.salesLogin}>
        <h1>KennUWare Sales</h1>
      </div>
    );
  }
}

SalesLogin.propTypes = {
  onLogin: React.PropTypes.func,
};

const mapStateToProps = selectSalesLogin();

function mapDispatchToProps(dispatch) {
  return {
    onLogin: () => dispatch(login()),
    dispatch,
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(SalesLogin);
