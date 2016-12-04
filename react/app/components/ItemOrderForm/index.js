/**
*
* ItemOrderForm
*
*/

import React from 'react';

import Input from 'components/Input';

import { Row, Col, Button } from 'react-bootstrap';

import styles from './styles.css';

class ItemOrderForm extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      itemID: '',
      itemName: '',
      itemQuantity: '',
      itemUnitPrice: '',
    };
  }

  render() {
    const labelStyle = {
      width: '90px',
    };
    return (
      <div className={styles.itemOrderForm}>
        <h2>Add Item</h2>
        <Input
          label="Item ID"
          labelStyle={labelStyle}
          value={this.state.itemID}
          onChange={(itemID) => {
            this.setState({ itemID });
          }}
        />
        <Input
          label="Item Name"
          labelStyle={labelStyle}
          value={this.state.itemName}
          onChange={(itemName) => {
            this.setState({ itemName });
          }}
        />
        <Input
          label="Quantity"
          labelStyle={labelStyle}
          value={this.state.itemQuantity}
          onChange={(itemQuantity) => {
            this.setState({ itemQuantity });
          }}
        />
        <Input
          label="Unit Price"
          labelStyle={labelStyle}
          value={this.state.itemUnitPrice}
          onChange={(itemUnitPrice) => {
            this.setState({ itemUnitPrice });
          }}
        />
        <div>
          <Button
            className={styles.button}
            bsStyle="primary"
            bsSize="small"
            onClick={() => this.props.onAddItem({ name: this.state.itemName, unitPrice: this.state.itemUnitPrice, id: this.state.itemID }, this.state.itemQuantity)}
          >
            Add item
          </Button>
          <Button
            className={styles.button}
            bsStyle="warning"
            bsSize="small"
            onClick={() => this.setState({ itemID: '', itemName: '', itemQuantity: '', itemUnitPrice: '' })}
          >
            Clear
          </Button>
        </div>
      </div>
    );
  }
}

ItemOrderForm.propTypes = {
  onAddItem: React.PropTypes.func,
};



export default ItemOrderForm;
