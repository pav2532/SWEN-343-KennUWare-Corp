/*
 *
 * Sales
 *
 */

import React from 'react';
import { connect } from 'react-redux';

import { createStructuredSelector } from 'reselect';

import selectSales from './selectors';
import { selectEmployee } from 'containers/App/selectors';

import AccountInfo from 'components/AccountInfo';
import SideNav from 'components/SideNav';
import TotalRevenue from 'components/TotalRevenue';
import ShoppingCart from 'components/ShoppingCart';
import GenericModal from 'components/GenericModal';

import {
  goToDashboard,
  goToOrderEditor,
  goToUserManagement,

  addToCart,
  removeFromCart,

  setPaymentInfoName,
  setPaymentInfoCCNumber,
  setPaymentInfoExpiration,

  checkout,
  newOrder,
  continueOrder,
} from './actions.js';

import {
  ASSOCIATE,
  REGIONALMANAGER,
  GENERALMANAGER,
} from 'containers/SalesLogin/constants';

import {
  signOut,
} from 'containers/App/actions';

import { Button } from 'react-bootstrap';
import ItemOrderForm from 'components/ItemOrderForm';
import PaymentForm from 'components/PaymentForm';

import styles from './styles.css';

export class SalesManagerPage extends React.Component { // eslint-disable-line react/prefer-stateless-function
  constructor(props) {
    super(props);

    this.state = {
      itemName: '',
      itemQuantity: 0,
      itemUnitPrice: 0.0,
    };
  }

  render() {
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
    // Determine the content to show
    let activeRoute = 'Dashboard';
    let content = (<div>Hello</div>);
    if (this.props.sales.content === 'dashboard') {
      activeRoute = 'Dashboard';
      content = (
        <TotalRevenue />
      );
    } else if (this.props.sales.content === 'orderEditor') {
      activeRoute = 'Bulk Order';
      content = (
        <div>
          {successModal}
          {errorModal}
          <div style={{ width: '50%', float: 'left', height: '600px' }}>
            <ItemOrderForm onAddItem={this.props.onAddItemToCart} />
            <ShoppingCart items={this.props.sales.shoppingCart} />
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
            <Button bsStyle="primary" bsSize="lg" onClick={this.props.onCheckout}>
              Checkout
            </Button>
          </div>
        </div>
      );
    } else if (this.props.sales.content === 'userManagement') {
      activeRoute = 'User Management';
      content = (
        <div>
          <h2>User Management</h2>
        </div>
      );
    }

    const navRoutes = [
      { label: 'Dashboard', onClick: this.props.onGoToDashboard },
      { label: 'User Management', onClick: this.props.onGoToUserManagement },
    ];
    if (this.props.employee.type === GENERALMANAGER) {
      navRoutes.push(
      { label: 'Bulk Order', onClick: this.props.onGoToOrderEditor }
      );
    }

    return (
      <div>
        <SideNav className={styles.sideNav} routes={navRoutes} active={activeRoute} />
        <AccountInfo
          className={styles.accountInfo}
          name={this.props.employee.username}
          employeeType={this.props.employee.type}

          onSignOut={this.props.onSignOut}
        />
        <div className={styles.content}>
          {content}
        </div>
      </div>
    );
  }
}

SalesManagerPage.propTypes = {
  authenticated: React.PropTypes.bool,
  employee: React.PropTypes.object,
  sales: React.PropTypes.object,
  shoppingCart: React.PropTypes.array,

  onGoToDashboard: React.PropTypes.func,
  onGoToOrderEditor: React.PropTypes.func,
  onGoToUserManagement: React.PropTypes.func,

  setPaymentInfoName: React.PropTypes.func,
  setPaymentInfoCCNumber: React.PropTypes.func,
  setPaymentInfoExpiration: React.PropTypes.func,

  onCheckout: React.PropTypes.func,
  onAddItemToCart: React.PropTypes.func,
  onNewOrder: React.PropTypes.func,
  onContinueOrder: React.PropTypes.func,

  onSignOut: React.PropTypes.func,
};

const mapStateToProps = createStructuredSelector({
  sales: selectSales(),
  employee: selectEmployee(),
});

function mapDispatchToProps(dispatch) {
  return {
    onGoToDashboard: () => dispatch(goToDashboard()),
    onGoToOrderEditor: () => dispatch(goToOrderEditor()),
    onGoToUserManagement: () => dispatch(goToUserManagement()),

    onAddItemToCart: (item, quantity) => dispatch(addToCart(item, quantity)),

    setPaymentInfoName: (value) => dispatch(setPaymentInfoName(value)),
    setPaymentInfoCCNumber: (value) => dispatch(setPaymentInfoCCNumber(value)),
    setPaymentInfoExpiration: (value) => dispatch(setPaymentInfoExpiration(value)),

    onCheckout: () => dispatch(checkout()),
    onNewOrder: () => dispatch(newOrder()),
    onContinueOrder: () => dispatch(continueOrder()),

    onSignOut: () => dispatch(signOut()),

    dispatch,
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(SalesManagerPage);
