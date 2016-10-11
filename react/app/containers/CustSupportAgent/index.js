/*
 *
 * CustSupportAgent
 *
 */

import React from 'react';
import { connect } from 'react-redux';
import { createStructuredSelector } from 'reselect';

import selectCustSupportAgent from './selectors';
import { selectEmployee } from 'containers/App/selectors';
import {
  signOut,
} from 'containers/App/actions';

import {
  gotoNewReturn,
  gotoReturns,
} from './actions';

import styles from './styles.css';

import SideNav from 'components/SideNav';
import AccountInfo from 'components/AccountInfo';

export class CustSupportAgent extends React.Component { // eslint-disable-line react/prefer-stateless-function
  render() {
    let activeRoute = 'New Return';
    let content = (<div>Hello</div>);
    if (this.props.page.content === 'newReturn') {
      activeRoute = 'New Return';
      content = (
        <div>New Return</div>
      );
    } else if (this.props.page.content === 'returns') {
      activeRoute = 'Returns';
      content = (
        <div>
          Returns
        </div>
      );
    }

    const navRoutes = [
      { label: 'New Return', onClick: this.props.onGoToNewReturn },
      { label: 'Returns', onClick: this.props.onGoToReturns },
    ];

    return (
      <div className={styles.custSupportAgent}>
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

CustSupportAgent.propTypes = {
  page: React.PropTypes.object,
  employee: React.PropTypes.object,

  onGoToNewReturn: React.PropTypes.func,
  onGoToReturns: React.PropTypes.func,

  onSignOut: React.PropTypes.func,
};

const mapStateToProps = createStructuredSelector({
  page: selectCustSupportAgent(),
  employee: selectEmployee(),
});

function mapDispatchToProps(dispatch) {
  return {
    onGoToNewReturn: () => dispatch(gotoNewReturn()),
    onGoToReturns: () => dispatch(gotoReturns()),

    onSignOut: () => dispatch(signOut()),

    dispatch,
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(CustSupportAgent);
