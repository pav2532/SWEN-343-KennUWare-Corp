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
    return this.props.returns.map((item) =>
      <tr key={item.id} className={styles.tableRow}>
        <td className={styles.tableCell}>{item.id}</td>
        <td className={styles.tableCell}>{item.itemID}</td>
        <td className={styles.tableCell}>{item.reason}</td>
        <td className={styles.tableCell}>{item.storeID}</td>
        <td className={styles.tableCell}>{item.type}</td>
      </tr>
    );
  }

  render() {
    const content = this.getContent();
    return (
      <div className={styles.returnTable}>
        <table className={styles.displayTable}>
          <tr className={styles.tableRow}>
            <th className={styles.tableHeader}>ID</th>
            <th className={styles.tableHeader}>Item Name</th>
            <th className={styles.tableHeader}>Reason for Return</th>
            <th className={styles.tableHeader}>Store ID</th>
            <th className={styles.tableHeader}>Type</th>
          </tr>
          {content}
        </table>
      </div>
    );
  }
}

ReturnTable.propTypes = {
  returns: React.PropTypes.array,
  getReturns: React.PropTypes.func,
}

export default ReturnTable;
