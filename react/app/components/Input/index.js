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
    const type = this.props.type ? this.props.type : 'text';
    const value = this.props.value !== undefined ? this.props.value : this.state.value;
    return (
      <div className={styles.input}>
        <span className={styles.label} style={this.props.labelStyle}>{this.props.label}</span>
        <input className={styles.textBox} type={type} value={value} onChange={this.onChange} />
      </div>
    );
  }
}

Input.propTypes = {
  label: React.PropTypes.string,
  type: React.PropTypes.string,
  value: React.PropTypes.string,
  labelStyle: React.PropTypes.object,
  onChange: React.PropTypes.func,
};

export default Input;
