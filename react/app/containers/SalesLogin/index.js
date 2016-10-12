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

import LoginForm from 'components/LoginForm';

export class SalesLogin extends React.Component { // eslint-disable-line react/prefer-stateless-function
  render() {
    return (
      <div className={styles.salesLogin}>
        <div className={styles.loginFormContainer}>
          <LoginForm
            title="KennUWare Sales"
            buttonClassName={styles.loginButton}
            loginFunction={(credentials) => this.props.onLogin(credentials)}
          />
        </div>
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
    onLogin: (credentials) => dispatch(login(credentials)),
    dispatch,
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(SalesLogin);
