/**
*
* ReturnModal
*
*/

import React from 'react';

import { Modal, Button, Row, Col } from 'react-bootstrap';

import styles from './styles.css';

class ReturnModal extends React.Component { // eslint-disable-line react/prefer-stateless-function
  render() {
    console.log('item', this.props.item);
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
              Actions:
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
};

export default ReturnModal;
