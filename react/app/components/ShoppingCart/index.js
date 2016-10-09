/**
*
* ShoppingCart
*
*/

import React from 'react';

import ShoppingCartItem from 'components/ShoppingCartItem';

import styles from './styles.css';

class ShoppingCart extends React.Component { // eslint-disable-line react/prefer-stateless-function
  render() {
    const content = this.props.items.map((item) => {
      console.log(item);
      return (
        <ShoppingCartItem key={item.name} {...item} />
      );
    });
    return (
      <div className={styles.shoppingCart}>
        {content}
      </div>
    );
  }
}

ShoppingCart.propTypes = {
  items: React.PropTypes.array,
};

export default ShoppingCart;
