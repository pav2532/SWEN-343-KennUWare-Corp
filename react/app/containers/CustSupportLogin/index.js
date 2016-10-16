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
        <div className={styles.loginFormContainer}>
          <LoginForm
            title="KennUWare Customer Support"
            buttonClassName={styles.loginButton}
            loginFunction={(credentials) => this.props.onLogin(credentials)}
          />
        </div>
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
