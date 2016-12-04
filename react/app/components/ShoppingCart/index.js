/**
*
* ShoppingCart
*
*/

import React from 'react';

import ShoppingCartItem from 'components/ShoppingCartItem';

import styles from './styles.css';

import { Row, Col } from 'react-bootstrap';

class ShoppingCart extends React.Component { // eslint-disable-line react/prefer-stateless-function
  calculateSubtotal() {
    let total = 0.0;
    for (let i = 0; i < this.props.items.length; i += 1) {
      const lineItem = this.props.items[i];
      total += lineItem.quantity * lineItem.item.unitPrice;
    }

    return total;
  }

  render() {
    let top = 0;
    let content = this.props.items.map((lineItem) => {
      const { name, unitPrice } = lineItem.item;
      const currentTop = top;
      top += 20;
      return (
        <ShoppingCartItem
          key={name}
          name={name}
          unitPrice={unitPrice}
          quantity={lineItem.quantity}
          style={{ top: currentTop }}
        />
      );
    });
    const subtotalTop = top + 80 + 20;
    if (this.props.items.length === 0) {
      content = (<div>There are no items in the cart.</div>);
    }
    const subtotal = this.calculateSubtotal();
    return (
      <div className={styles.shoppingCart}>
        <div className={styles.labels}>
          <div className={styles.title}>
            <h2>Shopping Cart</h2>
          </div>
          <div className={styles.priceLabel}>
            Price
          </div>
          <div className={styles.quantityLabel}>
            Quantity
          </div>
        </div>
        <div className={styles.itemList}>
          {content}
        </div>
        <div className={styles.subtotalLabel} style={{ top: `${subtotalTop}px` }}>
          Subtotal: ${subtotal}
        </div>
      </div>
    );
  }
}

ShoppingCart.propTypes = {
  items: React.PropTypes.array,
};

export default ShoppingCart;
