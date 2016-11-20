/*
 *
 * SsologinPage
 *
 */

import React from 'react';
import { connect } from 'react-redux';
import selectSsologinPage from './selectors';
import styles from './styles.css';

export class SsologinPage extends React.Component { // eslint-disable-line react/prefer-stateless-function
  render() {
    return (
      <div className={styles.ssologinPage}>
      </div>
    );
  }
}

const mapStateToProps = selectSsologinPage();

function mapDispatchToProps(dispatch) {
  return {
    dispatch,
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(SsologinPage);
