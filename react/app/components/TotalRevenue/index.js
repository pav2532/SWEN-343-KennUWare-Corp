/**
*
* TotalRevenue
*
*/


import React from 'react';
import styles from './styles.css';

class TotalRevenue extends React.Component {

  componentWillMount() {
    this.props.loadRevenue();
  }

  render() {
    return (
      <div>
        Revenue: {this.props.revenue}
      </div>
    );
  }
}



TotalRevenue.propTypes = {
  revenue: React.PropTypes.string,
  loadRevenue: React.PropTypes.func,
};

export default TotalRevenue;
