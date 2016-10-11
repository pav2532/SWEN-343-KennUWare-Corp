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

    this.state = {
      name: '',
      ccNumber: '',
      expiration: '',
    };
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
          onChange={(name) => {
            this.setState({ name });
            this.props.setName(name);
          }}
        />
        <Input
          label="Credit Card Number"
          labelStyle={labelStyle}
          onChange={(ccNumber) => {
            this.setState({ ccNumber });
            this.props.setCCNumber(ccNumber);
          }}
        />
        <Input
          label="Expiration"
          labelStyle={labelStyle}
          onChange={(expiration) => {
            this.setState({ expiration });
            this.props.setExpiration(expiration);
          }}
        />
      </div>
    );
  }
}

PaymentForm.propTypes = {
  setName: React.PropTypes.func,
  setCCNumber: React.PropTypes.func,
  setExpiration: React.PropTypes.func,
};

export default PaymentForm;
