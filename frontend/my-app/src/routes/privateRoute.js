import PropTypes from 'prop-types';
import React from 'react';
import { Redirect, Route } from 'react-router-dom';
import useAuth from '../hooks/useAuth';

const PrivateRoute = ({ children, ...rest }) => {
  const auth = useAuth();
  return (
    <Route
      // eslint-disable-next-line react/jsx-props-no-spreading
      {...rest}
      render={({ location }) => (auth.user ? (
        children
      ) : (
        <Redirect
          to={{
            pathname: '/login',
            state: {
              from: location,
            },
          }}
        />
      ))}
    />
  );
};

PrivateRoute.propTypes = {
  children: PropTypes.element.isRequired,
};

export default PrivateRoute;
