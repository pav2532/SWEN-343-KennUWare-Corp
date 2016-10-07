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
      securityCode: '',
    };
  }

  render() {
    return (
      <div className={styles.paymentForm}>
        <h2>Payment Info</h2>
        <Input
          label="Name On Card"
          onChange={(name) => {
            this.setState({ name });
            this.props.getPaymentInfo({ name });
          }}
        />
        <Input
          label="Credit Card Number"
          onChange={(ccNumber) => {
            this.setState({ ccNumber });
            this.props.getPaymentInfo({ ccNumber });
          }}
        />
        <Input
          label="Expiration"
          onChange={(expiration) => {
            this.setState({ expiration });
            this.props.getPaymentInfo({ expiration });
          }}
        />
        <Input
          label="Security Code"
          onChange={(securityCode) => {
            this.setState({ securityCode });
            this.props.getPaymentInfo({ securityCode });
          }}
        />
      </div>
    );
  }
}

PaymentForm.propTypes = {
  getPaymentInfo: React.PropTypes.func,
};

export default PaymentForm;
