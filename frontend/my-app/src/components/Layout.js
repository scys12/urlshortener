import styled from '@emotion/styled';
import {
  Toolbar,
} from '@material-ui/core';
import React, { useState } from 'react';
import { Outlet } from 'react-router-dom';
import Navbar from './Navbar';

const LayoutRoot = styled('div')({
  display: 'flex',
  height: '100vh',
  flexDirection: 'column',
});

const LayoutContent = styled('div')({
  height: '100%',
  display: 'flex',
});

const Layout = () => {
  const [, setMobileNavOpen] = useState(false);
  return (
    <LayoutRoot>
      <Navbar onMobileNavOpen={() => setMobileNavOpen(true)} />
      <Toolbar />
      <LayoutContent>
        <Outlet />
      </LayoutContent>
    </LayoutRoot>
  );
};

export default Layout;
