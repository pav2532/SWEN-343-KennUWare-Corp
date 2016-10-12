/**
*
* ReturnTable
*
*/

import React from 'react';


import styles from './styles.css';

class ReturnTable extends React.Component {
  componentWillMount() {
    this.props.getReturns();
  }

  getContent() {
    return this.props.returns.map((item) => <div key={item.id}>{item.id}</div>);
  }

  render() {
    const content = this.getContent();
    return (
      <div className={styles.returnTable}>
      {content}
      </div>
    );
  }
}

ReturnTable.propTypes = {
  returns: React.PropTypes.array,
  getReturns: React.PropTypes.func,
}

export default ReturnTable;
