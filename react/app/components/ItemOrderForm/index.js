/**
*
* ItemOrderForm
*
*/

import React from 'react';

import Input from 'components/Input';

import styles from './styles.css';

class ItemOrderForm extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      modelNumber: '',
      quantity: '',
    };
  }

  render() {
    return (
      <div className={styles.itemOrderForm}>
        <h2>Payment Info</h2>
        <Input
          label="Model Number"
          onChange={(modelNumber) => {
            this.setState({ modelNumber });
            this.props.getOrderInfo({ modelNumber });
          }}
        />
        <Input
          label="Quantity"
          onChange={(quantity) => {
            this.setState({ quantity });
            this.props.getOrderInfo({ quantity });
          }}
        />
      </div>
    );
  }
}

ItemOrderForm.propTypes = {
  getOrderInfo: React.PropTypes.func,
};



export default ItemOrderForm;
