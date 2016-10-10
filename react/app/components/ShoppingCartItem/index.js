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
      <div className={styles.shoppingCartItem} style={this.props.style}>
        <div className={styles.itemName}>{this.props.name}</div>
        <div className={styles.unitPrice}>${this.props.unitPrice}</div>
        <div className={styles.quantity}>{this.props.quantity}</div>
      </div>
    );
  }
}

ShoppingCartItem.propTypes = {
  name: React.PropTypes.string,
  quantity: React.PropTypes.number,
  unitPrice: React.PropTypes.number,
  style: React.PropTypes.object,
};

export default ShoppingCartItem;
