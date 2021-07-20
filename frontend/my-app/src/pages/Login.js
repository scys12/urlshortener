import {
  Box, Button, Container, Grid, TextField, Typography,
} from '@material-ui/core';
import { Facebook } from '@material-ui/icons';
import { makeStyles } from '@material-ui/styles';
import { Formik } from 'formik';
import React from 'react';
import * as Yup from 'yup';

const useStyles = makeStyles({
  boxWrapper: {
    display: 'flex',
    justifyContent: 'center',
    flexDirection: 'column',
    width: '100%',
  },
  boxSignIn: { marginBottom: 3 },
});

function Login() {
  const classes = useStyles();
  return (
    <Box className={classes.boxWrapper}>
      <Container maxWidth="sm">
        <Formik
          initialValues={{
            email: '',
            password: '',
          }}
          validationSchema={Yup.object().shape({
            email: Yup.string().email('Must be a valid email').max(50).required('Email is required'),
            password: Yup.string().max(50).required('Password is required'),
          })}
        >
          {({
            errors,
            handleBlur,
            handleChange,
            handleSubmit,
            isSubmitting,
            touched,
            values,
          }) => (
            <form onSubmit={handleSubmit}>
              <Box className={classes.boxSignIn}>
                <Typography
                  color="textSecondary"
                  gutterBottom
                  variant="body2"
                >
                  Sign In
                </Typography>
              </Box>
              <Grid
                container
                spacing={3}
              >
                <Grid
                  item
                  xs={12}
                  md={6}
                >
                  <Button
                    color="primary"
                    fullWidth
                    startIcon={<Facebook />}
                    onClick={handleSubmit}
                    size="large"
                    variant="contained"
                  >
                    Login with Facebook
                  </Button>
                </Grid>
                <Grid
                  item
                  xs={12}
                  md={6}
                >
                  <Button
                    color="primary"
                    fullWidth
                    startIcon={<Facebook />}
                    onClick={handleSubmit}
                    size="large"
                    variant="contained"
                  >
                    Login with Facebook
                  </Button>
                </Grid>
              </Grid>
              <Box>
                <Typography>
                  Sign in with email
                </Typography>
              </Box>
              <TextField
                error={Boolean(touched.email && errors.email)}
                fullWidth
                helperText={touched.email && errors.email}
                label="Email Address"
                margin="normal"
                name="email"
                onBlur={handleBlur}
                onChange={handleChange}
                type="email"
                value={values.email}
                variant="outlined"
              />
              <TextField
                error={Boolean(touched.password && errors.password)}
                fullWidth
                helperText={touched.password && errors.password}
                label="Password"
                margin="normal"
                name="password"
                onBlur={handleBlur}
                onChange={handleChange}
                type="password"
                value={values.password}
                variant="outlined"
              />
              <Box sx={{ py: 2 }}>
                <Button
                  color="primary"
                  disabled={isSubmitting}
                  fullWidth
                  size="large"
                  type="submit"
                  variant="contained"
                >
                  Sign in now!
                </Button>
              </Box>
            </form>
          )}
        </Formik>
      </Container>
    </Box>
  );
}

export default Login;
