/**
*
* SideNav
*
*/

import React from 'react';

import { Button } from 'react-bootstrap';

import styles from './styles.css';

function SideNav(props) {
  const routes = props.routes.map((route) => {
    console.log(route);
    return (
      <Button key={route.label} bsStyle="success" bsSize="large" onClick={route.onClick}>
        {route.label}
      </Button>
    );
  });
  return (
    <div className={props.className}>
      <h1>KennUWare</h1>
      <h2>Sales</h2>
      {routes}
    </div>
  );
}

SideNav.propTypes = {
  className: React.PropTypes.string,
  routes: React.PropTypes.array,
};

export default SideNav;
