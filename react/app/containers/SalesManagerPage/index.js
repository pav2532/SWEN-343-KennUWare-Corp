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

import {
  goToDashboard,
  goToOrderEditor,
  goToUserManagement,
} from './actions.js';

import styles from './styles.css';

export class SalesManagerPage extends React.Component { // eslint-disable-line react/prefer-stateless-function
  render() {
    console.log(this.props.authenticated);
    console.log(this.props.employee);

    // Determine the content to show
    let content = (<div>Hello</div>);
    if (this.props.sales.content === 'dashboard') {
      content = (
        <TotalRevenue />
      );
    } else if (this.props.sales.content === 'orderEditor') {
      content = (
        <div>
          <h2>Order Editor</h2>
        </div>
      );
    } else if (this.props.sales.content === 'userManagement') {
      content = (
        <div>
          <h2>User Management</h2>
        </div>
      );
    }

    const navRoutes = [
      { label: 'Dashboard', onClick: this.props.onGoToDashboard },
      { label: 'Order Editor', onClick: this.props.onGoToOrderEditor },
      { label: 'User Management', onClick: this.props.onGoToUserManagement },
    ];

    return (
      <div>
        <SideNav className={styles.sideNav} routes={navRoutes} />
        <AccountInfo
          className={styles.accountInfo}
          name={this.props.employee.username}
          employeeType={this.props.employee.type}
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

  onGoToDashboard: React.PropTypes.func,
  onGoToOrderEditor: React.PropTypes.func,
  onGoToUserManagement: React.PropTypes.func,
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

    dispatch,
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(SalesManagerPage);
