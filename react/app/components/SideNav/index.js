/**
*
* SideNav
*
*/

import React from 'react';

import { Row, Col, Nav, NavItem } from 'react-bootstrap';

import styles from './styles.css';


function SideNav(props) {
  const routes = props.routes.map((route) =>
    <NavItem eventKey={route.label} key={route.label} onClick={route.onClick}>
      {route.label}
    </NavItem>
  );
  return (
    <div className={props.className}>
      <Row>
        <Col md={12}>
          <h1 className={styles.title}>TERI</h1>
        </Col>
      </Row>
      <Row>
        <Col md={12}>
          <Nav bsStyle="pills" activeKey={props.active} stacked>
            {routes}
          </Nav>
        </Col>
      </Row>
    </div>
  );
}

SideNav.propTypes = {
  className: React.PropTypes.string,
  active: React.PropTypes.string,
  routes: React.PropTypes.array,
};

export default SideNav;
