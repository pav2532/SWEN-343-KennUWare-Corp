/**
*
* Input
*
*/

import React from 'react';


import styles from './styles.css';

class Input extends React.Component {

  constructor(props) {
    super(props);

    this.onChange = this.onChange.bind(this);

    this.state = {
      value: '',
    };
  }

  onChange(evt) {
    this.setState({ value: evt.target.value });
    this.props.onChange(evt.target.value);
  }

  render() {
    return (
      <div className={styles.input}>
        <div>{this.props.label}</div>
        <input className={styles.textBox} type="text" value={this.state.value} onChange={this.onChange} />
      </div>
    );
  }
}

Input.propTypes = {
  label: React.PropTypes.string,
  onChange: React.PropTypes.func,
};

export default Input;
