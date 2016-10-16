/**
*
* CustomerInfo
*
*/

import React from 'react';


import styles from './styles.css';

import Input from 'components/Input';

function CustomerInfo(props) {
  const labelStyle = {
    width: '80px',
  };
  return (
    <div className={styles.customerInfo}>
      <h2>Customer Info</h2>
      <Input
        label="Name"
        labelStyle={labelStyle}
        value={props.name}
        onChange={(name) => {
          props.setName(name);
        }}
      />
      <Input
        label="Address"
        labelStyle={labelStyle}
        value={props.address}
        onChange={(name) => {
          props.setAddress(name);
        }}
      />
    </div>
  );
}

CustomerInfo.propTypes = {
  name: React.PropTypes.string,
  address: React.PropTypes.string,

  setName: React.PropTypes.func,
  setAddress: React.PropTypes.func,
};

export default CustomerInfo;
