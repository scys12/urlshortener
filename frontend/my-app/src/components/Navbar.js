import {
  AppBar, Box, Button, Toolbar, Typography,
} from '@material-ui/core';
import { makeStyles } from '@material-ui/styles';
import React from 'react';

const useStyles = makeStyles((theme) => ({
  box: {
    flexGrow: 1,
  },
  appBar: {
    flexDirection: 'column',
  },
  button: {
    margin: '0px 10px',
    '&:hover': {
      backgroundColor: theme.palette.background.paper,
      color: theme.palette.primary.main,
    },
  },
}));

function Navbar() {
  const classes = useStyles();
  return (
    <AppBar className={classes.appBar}>
      <Toolbar>
        <Typography variant="h3">
          Pendek.in
        </Typography>
        <Box className={classes.box} />
        <Button
          className={classes.button}
          color="inherit"
          href="/login"
        >
          <Typography variant="h5">
            Login
          </Typography>
        </Button>
        <Button
          className={classes.button}
          color="inherit"
          href="/register"
        >
          <Typography variant="h5">
            Register
          </Typography>
        </Button>
      </Toolbar>
    </AppBar>
  );
}

export default Navbar;
