/*
 *
 * SalesAssociatePage
 *
 */

import React from 'react';
import { connect } from 'react-redux';
import { createStructuredSelector } from 'reselect';

import selectSalesAssociatePage from './selectors';
import { selectEmployee } from 'containers/App/selectors';
import styles from './styles.css';


import {
  signOut,
} from 'containers/App/actions';

import {
  addToCart,
  removeFromCart,

  setPaymentInfoName,
  setPaymentInfoCCNumber,
  setPaymentInfoExpiration,

  checkout,
  newOrder,
  continueOrder,
  enterPage,
} from './actions.js';

import { Button, Modal } from 'react-bootstrap';

import AccountInfo from 'components/AccountInfo';
import ItemOrderForm from 'components/ItemOrderForm';
import PaymentForm from 'components/PaymentForm';
import ShoppingCart from 'components/ShoppingCart';
import GenericModal from 'components/GenericModal';

function paymentInfoComplete(paymentInfo) {
  return paymentInfo.name && paymentInfo.ccNumber && paymentInfo.expiration;
}

export class SalesAssociatePage extends React.Component { // eslint-disable-line react/prefer-stateless-function
  constructor(props) {
    super(props);

    this.state = {
      itemName: '',
      itemQuantity: 0,
      itemUnitPrice: 0.0,
    };
  }

  componentDidMount() {
    this.props.onEnterPage();
  }
  render() {
    let buttonStyle = 'primary';
    if (this.props.sales.shoppingCart.length === 0 || !paymentInfoComplete(this.props.sales.paymentInfo)) {
      buttonStyle += ' disabled';
    }
    const successModal = (
      <GenericModal
        show={this.props.sales.successModal}
        title="Success"
        body="The transaction completed successfully."
        buttonLabel="New Order"
        onButtonClick={this.props.onNewOrder}
      />
    );
    const errorModal = (
      <GenericModal
        show={this.props.sales.errorModal}
        title="Failure"
        body="There was an error with the payment information."
        buttonLabel="Edit Order"
        onButtonClick={this.props.onContinueOrder}
      />
    );
    return (
      <div className={styles.salesAssociatePage}>
        {successModal}
        {errorModal}
        <div className={styles.title}>
          <h1>KennUWare Sales</h1>
        </div>
        <div className={styles.accountInfo}>
          <AccountInfo
            name={this.props.employee.username}
            employeeType={this.props.employee.type}
            onSignOut={this.props.onSignOut}
          />
        </div>
        <div className={styles.orderEntry} style={{ width: '50%', float: 'left', height: '600px' }}>
          <div className={styles.itemEntry}>
            <ItemOrderForm onAddItem={this.props.onAddItemToCart} />
          </div>
          <div className={styles.shoppingCart}>
            <ShoppingCart items={this.props.sales.shoppingCart} />
          </div>
        </div>
        <div className={styles.paymentForm}>
          <PaymentForm
            name={this.props.sales.paymentInfo.name}
            ccNumber={this.props.sales.paymentInfo.ccNumber}
            expiration={this.props.sales.paymentInfo.expiration}

            setName={this.props.setPaymentInfoName}
            setCCNumber={this.props.setPaymentInfoCCNumber}
            setExpiration={this.props.setPaymentInfoExpiration}
          />
        </div>
        <div className={styles.checkoutButton}>
          <Button bsStyle={buttonStyle} bsSize="lg" onClick={this.props.onCheckout}>
            Checkout
          </Button>
        </div>
      </div>
    );
  }
}

SalesAssociatePage.propTypes = {
  employee: React.PropTypes.object,
  sales: React.PropTypes.array,
  onAddItemToCart: React.PropTypes.func,

  setPaymentInfoName: React.PropTypes.func,
  setPaymentInfoCCNumber: React.PropTypes.func,
  setPaymentInfoExpiration: React.PropTypes.func,

  onCheckout: React.PropTypes.func,
  onNewOrder: React.PropTypes.func,
  onContinueOrder: React.PropTypes.func,

  onEnterPage: React.PropTypes.func,
  onSignOut: React.PropTypes.func,
};

const mapStateToProps = createStructuredSelector({
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
    onNewOrder: () => dispatch(newOrder()),
    onContinueOrder: () => dispatch(continueOrder()),

    onEnterPage: () => dispatch(enterPage()),
    onSignOut: () => dispatch(signOut()),

    dispatch,
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(SalesAssociatePage);
