/*
 *
 * SalesAssociatePage
 *
 */

import React from 'react';
import { connect } from 'react-redux';
import selectSalesAssociatePage from './selectors';
import { selectEmployee } from 'containers/App/selectors';
import styles from './styles.css';


import {
  addToCart,
  removeFromCart,

  setPaymentInfoName,
  setPaymentInfoCCNumber,
  setPaymentInfoExpiration,

  checkout,
} from './actions.js';

import { Button } from 'react-bootstrap';

import ItemOrderForm from 'components/ItemOrderForm';
import PaymentForm from 'components/PaymentForm';
import ShoppingCart from 'components/ShoppingCart';

export class SalesAssociatePage extends React.Component { // eslint-disable-line react/prefer-stateless-function
  constructor(props) {
    super(props);

    this.state = {
      itemName: '',
      itemQuantity: 0,
      itemUnitPrice: 0.0,
    };
  }

  render() {
    console.log("Props");
    console.log(this.props);
    return (
      <div className={styles.salesAssociatePage}>
        <div style={{ width: '50%', float: 'left', height: '600px' }}>
          <ItemOrderForm onAddItem={this.props.onAddItemToCart} />
          <ShoppingCart items={this.props.shoppingCart} />
        </div>
        <PaymentForm
          setName={this.props.setPaymentInfoName}
          setCCNumber={this.props.setPaymentInfoCCNumber}
          setExpiration={this.props.setPaymentInfoExpiration}
        />
        <div>
          <Button bsStyle="success" bsSize="lg" onClick={this.props.onCheckout}>
            Checkout
          </Button>
        </div>
      </div>
    );
  }
}

SalesAssociatePage.propTypes = {
  shoppingCart: React.PropTypes.array,
  onAddItemToCart: React.PropTypes.func,

  setPaymentInfoName: React.PropTypes.func,
  setPaymentInfoCCNumber: React.PropTypes.func,
  setPaymentInfoExpiration: React.PropTypes.func,

  onCheckout: React.PropTypes.func,
};

const mapStateToProps = selectSalesAssociatePage({
  sales: selectSalesAssociatePage(),
  employee: selectEmployee(),

});

function mapDispatchToProps(dispatch) {
  return {
    onAddItemToCart: (item, quantity) => dispatch(addToCart(item, quantity)),

    setPaymentInfoName: (value) => dispatch(setPaymentInfoName(value)),
    setPaymentInfoCCNumber: (value) => dispatch(setPaymentInfoCCNumber(value)),
    setPaymentInfoExpiration: (value) => dispatch(setPaymentInfoExpiration(value)),

    onCheckout: () => dispatch(checkout()),

    dispatch,
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(SalesAssociatePage);
