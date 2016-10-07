/**
*
* ShoppingCartItem
*
*/

import React from 'react';

import styles from './styles.css';

class ShoppingCartItem extends React.Component { // eslint-disable-line react/prefer-stateless-function
  render() {
    return (
      <div className={styles.shoppingCartItem}>
        <div>{this.props.name}</div>
        <div>{this.props.details}</div>
        <div>Quantity: {this.props.quantity}</div>
        <div>Unit Price: {this.props.unitPrice}</div>
      </div>
    );
  }
}

ShoppingCartItem.propTypes = {
  name: React.PropTypes.string,
  details: React.PropTypes.string,
  quantity: React.PropTypes.number,
  unitPrice: React.PropTypes.number,
};

export default ShoppingCartItem;
