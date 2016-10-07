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

export class SalesAssociatePage extends React.Component { // eslint-disable-line react/prefer-stateless-function
  render() {
    return (
      <div className={styles.salesAssociatePage}>
        <h1>Sales Associate</h1>
        <ItemOrderForm getOrderInfo={(obj) => console.log('order obj: ', obj)} />
        <PaymentForm getPaymentInfo={(obj) => console.log('object: ', obj)} />
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
