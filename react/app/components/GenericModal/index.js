/**
*
* GenericModal
*
*/

import React from 'react';

import { Button, Modal } from 'react-bootstrap';

class GenericModal extends React.Component {
  render() {
    return (
      <Modal show={this.props.show} onHide={this.close}>
        <Modal.Header>
          <Modal.Title>{this.props.title}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <p>{this.props.body}</p>
        </Modal.Body>
        <Modal.Footer>
          <Button onClick={this.props.onButtonClick}>{this.props.buttonLabel}</Button>
        </Modal.Footer>
      </Modal>
    );
  }
}

GenericModal.propTypes = {
  show: React.PropTypes.bool,
  title: React.PropTypes.string,
  body: React.PropTypes.string,
  buttonLabel: React.PropTypes.string,

  onButtonClick: React.PropTypes.func,
};

export default GenericModal;
