/**
*
* SideNav
*
*/

import React from 'react';

import { Navbar, Nav, NavItem } from 'react-bootstrap';

import styles from './styles.css';

function SideNav(props) {
  const routes = props.routes.map((route) => {
    return (
      <NavItem eventKey={route.label} key={route.label} onClick={route.onClick}>
        {route.label}
      </NavItem>
    );
  });
  return (
    <div className={props.className}>
      <Nav bsStyle="pills" activeKey={props.active} stacked>
        {routes}
      </Nav>
    </div>
  );
}

SideNav.propTypes = {
  className: React.PropTypes.string,
  active: React.PropTypes.string,
  routes: React.PropTypes.array,
};

export default SideNav;
