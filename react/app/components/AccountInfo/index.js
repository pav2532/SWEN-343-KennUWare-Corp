/**
*
* AccountInfo
*
*/

import React from 'react';

import { Button } from 'react-bootstrap';

// import styles from './styles.css';

function AccountInfo(props) {
  return (
    <div className={props.className}>
      <div>{props.name}</div>
      <div>{props.employeeType}</div>
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
