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
        <h1>Sales Associate</h1>
        <PaymentForm getPaymentInfo={(obj) => console.log('object: ', obj)} />
        <input value={this.state.itemName} onChange={(evt) => this.setState({ itemName: evt.target.value })} />
        q:<input value={this.state.itemQuantity} onChange={(evt) => this.setState({ itemQuantity: evt.target.value })} />
        up:<input value={this.state.itemUnitPrice} onChange={(evt) => this.setState({ itemUnitPrice: evt.target.value })} />
        <Button bsStyle="primary" bsSize="lg" onClick={() => this.props.onAddItemToCart({ name: this.state.itemName, unitPrice: this.state.itemUnitPrice }, this.state.itemQuantity)}>
          Add item
        </Button>
        <ShoppingCart items={this.props.shoppingCart} />
      </div>
    );
  }
}

SalesAssociatePage.propTypes = {
  shoppingCart: React.PropTypes.array,
  onAddItemToCart: React.PropTypes.func,
};

const mapStateToProps = selectSalesAssociatePage({
  sales: selectSalesAssociatePage(),
  employee: selectEmployee(),

});

function mapDispatchToProps(dispatch) {
  return {
    onAddItemToCart: (item, quantity) => dispatch(addToCart(item, quantity)),

    dispatch,
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(SalesAssociatePage);
