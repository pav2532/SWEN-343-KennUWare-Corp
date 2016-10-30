/**
*
* PaymentForm
*
* Expects a function as a prop: getPaymentInfo() that is set up
* to take the various fields of payment as arguments in a
* destructured object. The example function should look something like this:
*
* function getPaymentInfo({ name, ccNumber, expiration, securityCode}) {
*   if(name) setNameInState(name);
*   if(ccNumber) setCCNumberInState(ccNumber);
*   ...
* }
*
* It is important to not overwrite values that are null/undefined because
* they will not necessarily be passed in everytime.
*/

import React from 'react';

import Input from 'components/Input';

import styles from './styles.css';

class PaymentForm extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    const labelStyle = {
      width: '160px',
    };
    return (
      <div className={styles.paymentForm}>
        <h2>Payment Info</h2>
        <Input
          label="Name On Card"
          labelStyle={labelStyle}
          value={this.props.name}
          onChange={(name) => {
            this.props.setName(name);
          }}
        />
        <Input
          label="Credit Card Number"
          labelStyle={labelStyle}
          value={this.props.ccNumber}
          onChange={(ccNumber) => {
            this.props.setCCNumber(ccNumber);
          }}
        />
        <Input
          label="Expiration"
          labelStyle={labelStyle}
          value={this.props.expiration}
          onChange={(expiration) => {
            this.props.setExpiration(expiration);
          }}
        />
      </div>
    );
  }
}

PaymentForm.propTypes = {
  name: React.PropTypes.string,
  ccNumber: React.PropTypes.string,
  expiration: React.PropTypes.string,

  setName: React.PropTypes.func,
  setCCNumber: React.PropTypes.func,
  setExpiration: React.PropTypes.func,
};

export default PaymentForm;
