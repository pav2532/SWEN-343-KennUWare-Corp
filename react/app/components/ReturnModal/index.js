/**
*
* ReturnModal
*
*/

import React from 'react';

import { Modal, Button } from 'react-bootstrap';

import styles from './styles.css';

class ReturnModal extends React.Component { // eslint-disable-line react/prefer-stateless-function
  render() {
    return (
      <Modal show={this.props.show} onHide={this.close}>
        <Modal.Header>
          <Modal.Title>Return Modal</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <p>You want to manage this return</p>
        </Modal.Body>
        <Modal.Footer>
          <Button onClick={this.props.cancel}>Cancel</Button>
        </Modal.Footer>
      </Modal>
    );
  }
}

ReturnModal.propTypes = {
  show: React.PropTypes.bool,
  cancel: React.PropTypes.func,
};

export default ReturnModal;
