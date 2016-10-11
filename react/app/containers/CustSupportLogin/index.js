/*
 *
 * CustSupportLogin
 *
 */

import React from 'react';
import { connect } from 'react-redux';
import selectCustSupportLogin from './selectors';
import styles from './styles.css';

import {
  login,
} from './actions';

import LoginForm from 'components/LoginForm';

export class CustSupportLogin extends React.Component { // eslint-disable-line react/prefer-stateless-function
  render() {
    return (
      <div className={styles.custSupportLogin}>
        <h1>Customer Support</h1>
        <LoginForm
          loginFunction={(credentials) => this.props.onLogin(credentials)}
        />
      </div>
    );
  }
}

CustSupportLogin.propTypes = {
  onLogin: React.PropTypes.func,
};

const mapStateToProps = selectCustSupportLogin();

function mapDispatchToProps(dispatch) {
  return {
    onLogin: (credentials) => dispatch(login(credentials)),
    dispatch,
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(CustSupportLogin);
