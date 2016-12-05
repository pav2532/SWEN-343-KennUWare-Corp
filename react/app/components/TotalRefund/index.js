/**
*
* TotalRefund
*
*/

import React from 'react';


import styles from './styles.css';

class TotalRefund extends React.Component {

  componentWillMount() {
    this.props.loadRefund();
  }

  render() {
    return (
      <div className={styles.totalRefund}>
        <div>Total Refunds</div>
        <div>${this.props.refund}</div>
      </div>
    );
  }
}

TotalRefund.propTypes = {
  refund: React.PropTypes.string,
  loadRefund: React.PropTypes.func,
};

export default TotalRefund;
