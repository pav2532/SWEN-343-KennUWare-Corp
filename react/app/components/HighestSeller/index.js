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
    console.log("Highest Seller Data", this.props.highestSeller);
    return (
      <div className={styles.storeRevenue}>
        <div>Total Revenue</div>
        <div>${this.props.highestSeller}</div>
      </div>
    );
  }
}

HighestSeller.propTypes = {
  highestSeller: React.PropTypes.string,
  loadHighestSeller: React.PropTypes.func,
};

export default HighestSeller;
