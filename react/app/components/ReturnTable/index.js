/**
*
* ReturnTable
*
*/

import React from 'react';

import { Table, Button, Row, Col, Form, FormGroup, ControlLabel, FormControl } from 'react-bootstrap';

import styles from './styles.css';

class ReturnTable extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      displayedReturns: [],
      filter: '',
    };
  }

  componentWillMount() {
    this.props.getReturns();
  }

  componentWillReceiveProps(nextProps) {
    this.setState({ displayedReturns: nextProps.returns });
  }

  getContent() {
    return this.state.displayedReturns.map((item) =>
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

  filterReturns(filterText) {
    let newReturns = [];
    console.log('filtering');

    if (filterText !== '') {
      for (let i = 0; i < this.props.returns.length; i++) {
        const item = this.props.returns[i];
        if (!isNaN(filterText)) {
          if (item.id === parseInt(filterText, 10)) {
            newReturns.push(item);
          }
        } else if (item.itemID.toLowerCase().includes(filterText.toLowerCase()) || item.type.toLowerCase().includes(filterText.toLowerCase()) || item.reason.toLowerCase().includes(filterText.toLowerCase())) {
          newReturns.push(item);
        }
      }
    } else {
      newReturns = this.props.returns;
    }

    return newReturns;
  }

  updateFilter(evt) {
    this.setState({ filter: evt.target.value });
    this.setState({ displayedReturns: this.filterReturns(evt.target.value) });
  }

  render() {
    const content = this.getContent();
    return (
      <div className={styles.returnTable}>
        <Row style={{ marginBottom: '20px' }}>
          <Col md={6}>
            <Form inline>
              <FormGroup controlId="formInlineName">
                <ControlLabel>Filter Results:</ControlLabel>
                <FormControl type="text" placeholder="filter" value={this.state.filter} onChange={(evt) => this.updateFilter(evt)}/>
              </FormGroup>
            </Form>
          </Col>
        </Row>
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
