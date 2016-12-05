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


export class CustSupportLogin extends React.Component { // eslint-disable-line react/prefer-stateless-function
  componentDidMount() {
    this.props.onLogin();
  }

  render() {
    return (
      <div className={styles.custSupportLogin}>
        <h1>KennUWare Customer Support</h1>
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
