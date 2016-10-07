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
    return (
      <div className={styles.loginForm}>
        <Input
          label="Username"
          onChange={(username) => this.setState({ username })}
        />
        <Input
          label="Password"
          onChange={(password) => this.setState({ password })}
        />
        <Button bsStyle="primary" bsSize="large" onClick={this.login}>
          Submit
        </Button>
      </div>
    );
  }
}

LoginForm.propTypes = {
  loginFunction: React.PropTypes.func,
};

export default LoginForm;
