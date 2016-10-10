/**
*
* SideNav
*
*/

import React from 'react';

import styles from './styles.css';

function SideNav(props) {
  const routes = props.routes.map((route) => {
    let className = styles.menuItem;
    if (props.active === route.label) {
      className += ` ${styles.menuItemActive}`;
    }
    return (
      <button className={className} key={route.label} onClick={route.onClick}>
        {route.label}
      </button>
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
  active: React.PropTypes.string,
  routes: React.PropTypes.array,
};

export default SideNav;
