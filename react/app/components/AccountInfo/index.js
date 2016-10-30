/**
*
* AccountInfo
*
*/

import React from 'react';

import { Button } from 'react-bootstrap';

import {
  GENERALMANAGER,
  REGIONALMANAGER,
} from 'containers/SalesLogin/constants';

import {
  AGENT,
} from 'containers/CustSupportLogin/constants';

import styles from './styles.css';

function AccountInfo(props) {
  let employeeType = 'Associate';
  if (props.employeeType === REGIONALMANAGER) {
    employeeType = 'Regional Manager';
  } else if (props.employeeType === GENERALMANAGER) {
    employeeType = 'General Manager';
  } else if (props.employeeType === AGENT) {
    employeeType = 'Customer Support Agent';
  }
  return (
    <div className={props.className}>
      <div className={styles.name}>{props.name}</div>
      <div>{employeeType}</div>
      <div>
        <Button bsStyle="warning" bsSize="small" onClick={props.onSignOut}>
          Sign out
        </Button>
      </div>
    </div>
  );
}

AccountInfo.propTypes = {
  className: React.PropTypes.string,
  name: React.PropTypes.string,
  employeeType: React.PropTypes.string,

  onSignOut: React.PropTypes.func,
};

export default AccountInfo;
