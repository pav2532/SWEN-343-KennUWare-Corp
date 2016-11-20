/**
*
* ReturnTable
*
*/

import React from 'react';

import { Table, Button } from 'react-bootstrap';

import styles from './styles.css';

class ReturnTable extends React.Component {
  componentWillMount() {
    this.props.getReturns();
  }

  getContent() {
    return this.props.returns.map((item) =>
      <tr key={item.id}>
        <td>{item.id}</td>
        <td>{item.itemID}</td>
        <td>{item.reason}</td>
        <td>{item.storeID}</td>
        <td>{item.type}</td>
        <td><Button onClick={() => this.props.onManageReturn(item)}>Manage</Button></td>
      </tr>
    );
  }

  render() {
    const content = this.getContent();
    return (
      <div className={styles.returnTable}>
        <Table striped bordered condensed hover>
          <thead>
            <tr>
              <th>ID</th>
              <th>Item Name</th>
              <th>Reason for Return</th>
              <th>Store ID</th>
              <th>Type</th>
              <th>Manage</th>
            </tr>
          </thead>
          <tbody>
            {content}
          </tbody>
        </Table>
      </div>
    );
  }
}

ReturnTable.propTypes = {
  returns: React.PropTypes.array,
  getReturns: React.PropTypes.func,
  onManageReturn: React.PropTypes.func,
};

export default ReturnTable;
