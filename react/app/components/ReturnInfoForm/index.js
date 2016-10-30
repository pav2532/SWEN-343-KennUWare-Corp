/**
*
* ReturnInfoForm
*
*/

import React from 'react';


import styles from './styles.css';

import Input from 'components/Input';

function ReturnInfoForm(props) {
  const labelStyle = {
    width: '80px',
  };
  return (
    <div className={styles.returnInfoForm}>
      <h2>Return Info</h2>
      <Input
        label="Item ID"
        labelStyle={labelStyle}
        value={props.itemId}
        onChange={(id) => {
          props.setItemId(id);
        }}
      />
      <Input
        label="Reason"
        labelStyle={labelStyle}
        value={props.reason}
        onChange={(reason) => {
          props.setReason(reason);
        }}
      />
    </div>
  );
}

ReturnInfoForm.propTypes = {
  itemId: React.PropTypes.string,
  reason: React.PropTypes.string,

  setItemId: React.PropTypes.func,
  setReason: React.PropTypes.func,
};

export default ReturnInfoForm;
