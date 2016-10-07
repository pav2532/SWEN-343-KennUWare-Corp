/*
 *
 * SalesAssociatePage
 *
 */

import React from 'react';
import { connect } from 'react-redux';
import selectSalesAssociatePage from './selectors';
import styles from './styles.css';

import ItemOrderForm from 'components/ItemOrderForm';
import PaymentForm from 'components/PaymentForm';
import ShoppingCart from 'components/ShoppingCart';

export class SalesAssociatePage extends React.Component { // eslint-disable-line react/prefer-stateless-function
  render() {
    const items = [
      { name: 'fashionW', details: 'Lights up', quantity: 2, unitPrice: 30.05 },
      { name: 'healthyW', details: 'Lights up', quantity: 2, unitPrice: 50.05 },
      { name: 'ActiveW', details: 'Lights up', quantity: 2, unitPrice: 500.05 },
    ];
    return (
      <div className={styles.salesAssociatePage}>
        <h1>Sales Associate</h1>
        <ItemOrderForm getOrderInfo={(obj) => console.log('order obj: ', obj)} />
        <PaymentForm getPaymentInfo={(obj) => console.log('object: ', obj)} />
        <h2>Shopping Cart</h2>
        <ShoppingCart items={items} />
      </div>
    );
  }
}

const mapStateToProps = selectSalesAssociatePage();

function mapDispatchToProps(dispatch) {
  return {
    dispatch,
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(SalesAssociatePage);
