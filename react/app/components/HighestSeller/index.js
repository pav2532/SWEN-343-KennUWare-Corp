/**
*
* HighestSeller
*
*/

import React from 'react';


import styles from './styles.css';

class HighestSeller extends React.Component {

  componentWillMount() {
    this.props.loadHighestSeller();
  }

  render() {
    return (
      <div className={styles.storeRevenue}>
        <h2>Highest Seller</h2>
        <div>{this.props.highestSeller}</div>
      </div>
    );
  }
}

HighestSeller.propTypes = {
  highestSeller: React.PropTypes.string,
  loadHighestSeller: React.PropTypes.func,
};

export default HighestSeller;
