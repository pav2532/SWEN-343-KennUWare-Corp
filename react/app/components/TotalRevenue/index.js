/**
*
* TotalRevenue
*
*/


import React from 'react';
import styles from './styles.css';
import rd3 from 'rd3';
import { scaleOrdinal, schemeCategory10 } from 'd3-scale';

const BarChart = rd3.BarChart;

class TotalRevenue extends React.Component {

  componentWillMount() {
    this.props.loadRevenue();
  }

  render() {

    let barData = [
    {
      'name': 'Series A',
      'values': [
        { 'x': 1, 'y':  91 },
        { 'x': 2, 'y': 290 },
        { 'x': 3, 'y': -25 },
      ]
    },
    {  
      'name': 'Series B',
      'values': [
        { 'x': 1, 'y':  9 },
        { 'x': 2, 'y': 49 },
        { 'x': 3, 'y': -20 },
      ]
    },
    {  
      'name': 'Series C',
      'values': [
        { 'x': 1, 'y': 14 },
        { 'x': 2, 'y': 77 },
        { 'x': 3, 'y': -70 },
      ]
    }
  ];

    return (
      <div className={styles.totalRevenue}>
        
        <BarChart
        data={barData}
        width={500}
        colors={scaleOrdinal(schemeCategory10)}
        height={300}
        title='Bar Chart'
        xAxisLabel='Value'
        yAxisLabel='Label'
        />
      </div>
    );
  }
}



TotalRevenue.propTypes = {
  revenue: React.PropTypes.string,
  loadRevenue: React.PropTypes.func,
};

export default TotalRevenue;
