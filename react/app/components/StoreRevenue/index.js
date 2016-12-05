/**
*
* StoreRevenue
*
*/

import React from 'react';

import { BarChart } from 'react-d3';

import styles from './styles.css';

class StoreRevenue extends React.Component {

  componentWillMount() {
    this.props.loadRevenue();
  }

  convertData(data) {
    return data.map((item) => ({ y: item.revenue, x: item.store.name }));
  }

  render() {
    console.log("Store revenue data", this.props.revenue);
    const barData = [{ values: this.convertData(this.props.revenue) }];
    return (
      <div className={styles.storeRevenue}>
        <BarChart
          data={barData}
          width={400}
          height={400}
          title="Store Revenue"
        />
      </div>
    );
  }
}

StoreRevenue.propTypes = {
  revenue: React.PropTypes.string,
  loadRevenue: React.PropTypes.func,
};

export default StoreRevenue;
