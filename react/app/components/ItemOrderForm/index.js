/**
*
* ItemOrderForm
*
*/

import React from 'react';

import Input from 'components/Input';

import { Button } from 'react-bootstrap';

import styles from './styles.css';

class ItemOrderForm extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      itemQuantity: '',
    };
  }

  render() {
    const labelStyle = {
      width: '90px',
    };
    return (
      <div className={styles.itemOrderForm}>
        <h2>Add Item</h2>
        <Button onClick={() => this.props.selectItem(true)}>Choose Item</Button>
        <Input
          label="Item ID"
          labelStyle={labelStyle}
          value={this.props.orderItem.id}
        />
        <Input
          label="Item Name"
          labelStyle={labelStyle}
          value={this.props.orderItem.name}
        />
        <Input
          label="Unit Price"
          labelStyle={labelStyle}
          value={this.props.orderItem.unitPrice}
        />
        <Input
          label="Quantity"
          labelStyle={labelStyle}
          value={this.state.itemQuantity}
          onChange={(itemQuantity) => {
            this.setState({ itemQuantity });
          }}
        />
        <div>
          <Button
            className={styles.button}
            bsStyle="primary"
            bsSize="small"
            onClick={() => this.props.onAddItem({ name: this.props.orderItem.name, unitPrice: this.props.orderItem.unitPrice, id: this.props.orderItem.id }, this.state.itemQuantity)}
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
  orderItem: React.PropTypes.object,
  onAddItem: React.PropTypes.func,
  selectItem: React.PropTypes.func,
};



export default ItemOrderForm;
