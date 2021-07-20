import React from 'react';
import Layout from '../components/Layout';
import HomePage from '../pages/Homepage';
import Login from '../pages/Login';
import Register from '../pages/Register';

const routes = [
  {
    path: '/',
    element: <Layout />,
    children: [
      { path: '/', element: <HomePage /> },
      { path: '/login', element: <Login /> },
      { path: '/register', element: <Register /> },
    ],
  },
];

export default routes;
