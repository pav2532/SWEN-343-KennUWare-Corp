/**
*
* LoginForm
*
*/

import React from 'react';

import Input from 'components/Input';
import { Button } from 'react-bootstrap';


import styles from './styles.css';

class LoginForm extends React.Component {

  constructor(props) {
    super(props);

    this.login = this.login.bind(this);

    this.state = {
      username: '',
      password: '',
    };
  }

  login() {
    const { username, password } = this.state;
    this.props.loginFunction({ username, password });
  }

  render() {
    const labelStyle = {
      width: '80px',
    };
    return (
      <div className={styles.loginForm}>
        <h1>{this.props.title}</h1>
        <Input
          label="Username"
          labelStyle={labelStyle}
          onChange={(username) => this.setState({ username })}
        />
        <Input
          label="Password"
          type="password"
          labelStyle={labelStyle}
          onChange={(password) => this.setState({ password })}
        />
        <div className={styles.loginButtonDiv}>
          <Button className={`${styles.loginButton} ${this.props.buttonClassName}`} bsStyle="primary" bsSize="large" onClick={this.login}>
            Login
          </Button>
        </div>
      </div>
    );
  }
}

LoginForm.propTypes = {
  title: React.PropTypes.string,
  buttonClassName: React.PropTypes.string,
  loginFunction: React.PropTypes.func,
};

export default LoginForm;
