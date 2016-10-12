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

  setCustomerName,
  setCustomerAddress,
  setReturnReason,
  setReturnItemId,

  completeReturnRequest,
  editReturnRequest,

  getReturns,

  submitReturn,
} from './actions';

import styles from './styles.css';

import { Button, Modal } from 'react-bootstrap';

import SideNav from 'components/SideNav';
import AccountInfo from 'components/AccountInfo';
import NewReturnForm from 'components/NewReturnForm';
import GenericModal from 'components/GenericModal';
import ReturnTable from 'components/ReturnTable';

export class CustSupportAgent extends React.Component { // eslint-disable-line react/prefer-stateless-function
  render() {
    let activeRoute = 'New Return';
    let content = (<div>Hello</div>);
    const successModal = (
      <GenericModal
        show={this.props.page.successModal}
        title="Success"
        body="The transaction completed successfully."
        buttonLabel="New Return Request"
        onButtonClick={this.props.onNewRequest}
      />
    );
    const errorModal = (
      <GenericModal
        show={this.props.page.errorModal}
        title="Failure"
        body="There was an error with the payment information."
        buttonLabel="Edit Request"
        onButtonClick={this.props.onEditRequest}
      />
    );
    if (this.props.page.content === 'newReturn') {
      activeRoute = 'New Return';
      content = (
        <div>
          <NewReturnForm
            name={this.props.page.newReturn.customerName}
            address={this.props.page.newReturn.customerAddress}
            reason={this.props.page.newReturn.reason}
            itemId={this.props.page.newReturn.itemId}

            setName={this.props.onSetCustomerName}
            setAddress={this.props.onSetCustomerAddress}
            setReason={this.props.onSetReturnReason}
            setItemId={this.props.onSetReturnItemId}

            submitReturn={this.props.onSubmitReturn}
          />
        </div>
      );
    } else if (this.props.page.content === 'returns') {
      activeRoute = 'Returns';
      content = (
        <div>
          <ReturnTable
            returns={this.props.page.returns}
            getReturns={this.props.onGetReturns}
          />
        </div>
      );
    }

    const navRoutes = [
      { label: 'New Return', onClick: this.props.onGoToNewReturn },
      { label: 'Returns', onClick: this.props.onGoToReturns },
    ];

    return (
      <div className={styles.custSupportAgent}>
        {successModal}
        {errorModal}
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

  onSetCustomerName: React.PropTypes.func,
  onSetCustomerAddress: React.PropTypes.func,
  onSetReturnReason: React.PropTypes.func,
  onSetReturnItemId: React.PropTypes.func,

  onSubmitReturn: React.PropTypes.func,
  onNewRequest: React.PropTypes.func,
  onEditRequest: React.PropTypes.func,

  onGetReturns: React.PropTypes.func,

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

    onSetCustomerName: (value) => dispatch(setCustomerName(value)),
    onSetCustomerAddress: (value) => dispatch(setCustomerAddress(value)),
    onSetReturnReason: (value) => dispatch(setReturnReason(value)),
    onSetReturnItemId: (value) => dispatch(setReturnItemId(value)),

    onSubmitReturn: () => dispatch(submitReturn()),
    onNewRequest: () => dispatch(completeReturnRequest()),
    onEditRequest: () => dispatch(editReturnRequest()),

    onGetReturns: () => dispatch(getReturns()),

    onSignOut: () => dispatch(signOut()),

    dispatch,
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(CustSupportAgent);
