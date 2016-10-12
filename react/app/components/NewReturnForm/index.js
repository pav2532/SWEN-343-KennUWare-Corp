/**
*
* NewReturnForm
*
*/

import React from 'react';

import styles from './styles.css';

import { Button } from 'react-bootstrap';

import CustomerInfoForm from 'components/CustomerInfoForm';
import ReturnInfoForm from 'components/ReturnInfoForm';

class NewReturnForm extends React.Component { // eslint-disable-line react/prefer-stateless-function
  render() {
    let buttonClass = '';
    if (this.props.name === '' ||
        this.props.address === '' ||
        this.props.itemId === '' ||
        this.props.reason === '')
    {
      buttonClass = 'disabled';
    }
    return (
      <div className={styles.newReturnForm}>
        <CustomerInfoForm
          {...this.props}
        />
        <ReturnInfoForm
          {...this.props}
        />
        <div className={styles.submitButton}>
          <Button className={buttonClass} bsStyle="primary" bsSize="lg" onClick={() => this.props.submitReturn()}>
            Submit
          </Button>
        </div>
      </div>
    );
  }
}

NewReturnForm.propTypes = {
  name: React.PropTypes.string,
  address: React.PropTypes.string,
  reason: React.PropTypes.string,
  itemId: React.PropTypes.string,

  submitReturn: React.PropTypes.func,
};

export default NewReturnForm;
