/*
 *
 * SsologinPage
 *
 */

import React from 'react';
import { connect } from 'react-redux';

import selectSSOLoginPage from './selectors';

import {
  updatePassword,
  updateUsername,
  login,
} from './actions';

import styles from './styles.css';

import { Form, Panel, Row, Col, FormControl, FormGroup, ControlLabel, Button, Alert } from 'react-bootstrap';

export class SSOLoginPage extends React.Component { // eslint-disable-line react/prefer-stateless-function
  render() {
    let error = (<div></div>);
    if (this.props.error !== '') {
      error = (
        <Alert bsStyle="danger">
          {this.props.error}
        </Alert>
      );
    }
    return (
      <div className={styles.ssologinPage}>
        <Row className={styles.mainRow}>
          <Col md={4} mdOffset={4}>
            <Panel header="KennUWare Central Login" bsStyle="info">
              {error}
              <Form horizontal>
                <FormGroup controlId="formHorizontalEmail">
                  <Col componentClass={ControlLabel} sm={2}>
                    Username
                  </Col>
                  <Col sm={10}>
                    <FormControl type="text" placeholder="Username" value={this.props.username} onChange={(evt) => this.props.onUpdateUsername(evt.target.value)} />
                  </Col>
                </FormGroup>

                <FormGroup controlId="formHorizontalPassword">
                  <Col componentClass={ControlLabel} sm={2}>
                    Password
                  </Col>
                  <Col sm={10}>
                    <FormControl type="password" placeholder="Password" value={this.props.password} onChange={(evt) => this.props.onUpdatePassword(evt.target.value)} />
                  </Col>
                </FormGroup>

                <FormGroup>
                  <Col smOffset={2} sm={10}>
                    <Button bsStyle="primary" onClick={this.props.onLogin}>
                      Sign in
                    </Button>
                  </Col>
                </FormGroup>
              </Form>
            </Panel>
          </Col>
        </Row>
      </div>
    );
  }
}

SSOLoginPage.propTypes = {
  username: React.PropTypes.string,
  password: React.PropTypes.string,
  error: React.PropTypes.string,
  onUpdateUsername: React.PropTypes.func,
  onUpdatePassword: React.PropTypes.func,
  onLogin: React.PropTypes.func,
};

const mapStateToProps = selectSSOLoginPage();

function mapDispatchToProps(dispatch) {
  return {
    onUpdateUsername: (username) => dispatch(updateUsername(username)),
    onUpdatePassword: (password) => dispatch(updatePassword(password)),
    onLogin: () => dispatch(login()),
    dispatch,
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(SSOLoginPage);
