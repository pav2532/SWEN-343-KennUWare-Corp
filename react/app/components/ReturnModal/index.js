/**
*
* ReturnModal
*
*/

import React from 'react';

import { Modal, Button, Row, Col } from 'react-bootstrap';

import styles from './styles.css';

class ReturnModal extends React.Component { // eslint-disable-line react/prefer-stateless-function
  constructor(props) {
    super(props);

    this.state = {
      requestStatus: 'REFURBISH',
    };
  }

  render() {
    let actions = (
      <div>
        There are no actions to take for this return.
      </div>
    );
    if (this.props.item.type === 'PENDING') {
      // Set to refund/refurbish/denied
      actions = (
        <div>
          <Row>
            <Button bsStyle="success" onClick={() => this.props.onSetRequestStatus('refund')}>
              Refund
            </Button>
          </Row>
          <Row>
            <Button bsStyle="success" onClick={() => this.props.onSetRequestStatus('refurbish')}>
              Refurbish
            </Button>
          </Row>
          <Row>
            <Button bsStyle="success" onClick={() => this.props.onSetRequestStatus('deny')}>
              Deny
            </Button>
          </Row>
        </div>
      );
    } else if (this.props.item.type === 'REFUND' || this.props.item.type === 'REFURBISH') {
      // Mark as received / Mark as resolved? Is there a type that supports this?
      actions = (
        <div>
          <Button bsStyle="success" onClick={() => this.props.onResolveReturn()}>
            Mark as Resolved
          </Button>
        </div>
      );
    } else {
      // There are no actions to take
    }
    return (
      <Modal show={this.props.show} onHide={this.close}>
        <Modal.Header>
          <Modal.Title>Manage Return</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Row>
            <Col md={6}>
              <Row>
                <Col md={4} className={styles.labelText}>
                  ID:
                </Col>
                <Col md={8}>
                  {this.props.item.id}
                </Col>
              </Row>
              <Row>
                <Col md={4} className={styles.labelText}>
                  Reason:
                </Col>
                <Col md={8}>
                  {this.props.item.reason}
                </Col>
              </Row>
              <Row>
                <Col md={4} className={styles.labelText}>
                  Status:
                </Col>
                <Col md={8}>
                  {this.props.item.type}
                </Col>
              </Row>
              <Row>
                <Col md={4} className={styles.labelText}>
                  Item ID:
                </Col>
                <Col md={4}>
                  {this.props.item.itemID}
                </Col>
              </Row>
            </Col>
            <Col md={6}>
              {actions}
            </Col>
          </Row>
        </Modal.Body>
        <Modal.Footer>
          <Button onClick={this.props.cancel}>Done</Button>
        </Modal.Footer>
      </Modal>
    );
  }
}

ReturnModal.propTypes = {
  item: React.PropTypes.object,
  show: React.PropTypes.bool,
  cancel: React.PropTypes.func,
  onSetRequestStatus: React.PropTypes.func,
  onResolveReturn: React.PropTypes.func,
};

export default ReturnModal;
