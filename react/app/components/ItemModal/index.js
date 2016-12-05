/**
*
* ItemModal
*
*/

import React from 'react';

import { Row, Col, Button, Modal } from 'react-bootstrap';

import styles from './styles.css';

class ItemModal extends React.Component { // eslint-disable-line react/prefer-stateless-function
  constructor(props) {
    super(props);

    this.state = {
      requestStatus: 'REFURBISH',
    };
  }

  render() {
    return (
      <Modal show={this.props.show} onHide={this.close}>
        <Modal.Header>
          <Modal.Title>Select an Item</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Row>
            <Col md={12}>
              Item modal
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

ItemModal.propTypes = {
  show: React.PropTypes.bool,
  cancel: React.PropTypes.func,
};

export default ItemModal;
