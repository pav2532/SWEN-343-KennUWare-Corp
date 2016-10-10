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

import {
  goToDashboard,
  goToOrderEditor,
  goToUserManagement,

  addToCart,
  removeFromCart,
} from './actions.js';

import {
  signOut,
} from 'containers/App/actions';

import { Button } from 'react-bootstrap';
import ItemOrderForm from 'components/ItemOrderForm';

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
          <div style={{ width: '50%', float: 'left', height: '600px' }}>
            <ItemOrderForm onAddItem={this.props.onAddItemToCart} />
            <ShoppingCart items={this.props.sales.shoppingCart} />
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
    if (this.props.employee.type === 'GeneralManager') {
      navRoutes.push(
      { label: 'Bulk Order', onClick: this.props.onGoToOrderEditor },)
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

  onAddItemToCart: React.PropTypes.func,

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

    onSignOut: () => dispatch(signOut()),

    dispatch,
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(SalesManagerPage);
