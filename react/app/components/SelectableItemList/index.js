/**
*
* SelectableItemList
*
*/

import React from 'react';

import { ListGroup, ListGroupItem } from 'react-bootstrap';

import styles from './styles.css';

class SelectableItemList extends React.Component { // eslint-disable-line react/prefer-stateless-function
  onSelect(item) {
    this.props.onSelect(item);
    this.props.cancel();
  }
  render() {
    const itemList = this.props.items.map((item) => (
      <ListGroupItem header={`${item.id}: ${item.name}`} item={item} onClick={() => this.onSelect(item)}>
        <span className={styles.price}>${item.unitPrice}</span>
      </ListGroupItem>
    ));
    return (
      <div className={styles.selectableItemList}>
        <ListGroup>
          {itemList}
        </ListGroup>
      </div>
    );
  }
}

SelectableItemList.propTypes = {
  items: React.PropTypes.array,
  onSelect: React.PropTypes.func,
  cancel: React.PropTypes.func,
};

export default SelectableItemList;
