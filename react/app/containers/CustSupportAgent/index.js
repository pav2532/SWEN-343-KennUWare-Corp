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

  submitReturn,
} from './actions';

import styles from './styles.css';

import { Button, Modal } from 'react-bootstrap';

import SideNav from 'components/SideNav';
import AccountInfo from 'components/AccountInfo';
import NewReturnForm from 'components/NewReturnForm';

export class CustSupportAgent extends React.Component { // eslint-disable-line react/prefer-stateless-function
  render() {
    let activeRoute = 'New Return';
    let content = (<div>Hello</div>);
    const successModal = (
      <Modal show={this.props.page.successModal} onHide={this.close}>
        <Modal.Header>
          <Modal.Title>Success</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <p>The transaction completed successfully.</p>
        </Modal.Body>
        <Modal.Footer>
          <Button onClick={this.props.onNewRequest}>New Order</Button>
        </Modal.Footer>
      </Modal>
    );
    const errorModal = (
      <Modal show={this.props.page.errorModal} onHide={this.close}>
        <Modal.Header>
          <Modal.Title>Failure</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <p>There was an error with the payment information.</p>
        </Modal.Body>
        <Modal.Footer>
          <Button onClick={this.props.onEditRequest}>Edit Order</Button>
        </Modal.Footer>
      </Modal>
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

    onSignOut: () => dispatch(signOut()),

    dispatch,
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(CustSupportAgent);
