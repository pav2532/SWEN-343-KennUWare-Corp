/**
*
* ReturnModal
*
*/

import React from 'react';

import GenericModal from 'components/GenericModal';

import styles from './styles.css';

class ReturnModal extends React.Component { // eslint-disable-line react/prefer-stateless-function
  render() {
    return (
      <GenericModal
        show={this.props.show}
        title="Return Modal"
        body="You want to manage this return"
      />
    );
  }
}

ReturnModal.propTypes = {
  show: React.PropTypes.bool,
};

export default ReturnModal;
