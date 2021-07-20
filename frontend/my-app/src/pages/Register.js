import {
  Box, Button, Container, TextField, Typography,
} from '@material-ui/core';
import { makeStyles } from '@material-ui/styles';
import { Formik } from 'formik';
import React from 'react';
import { useNavigate } from 'react-router';

const useStyles = makeStyles({
  boxWrapper: {
    display: 'flex',
    justifyContent: 'center',
    flexDirection: 'column',
    width: '100%',
  },
  boxSignIn: { marginBottom: 3 },
});

function Register() {
  const classes = useStyles();
  const navigate = useNavigate();
  return (
    <Box className={classes.boxWrapper}>
      <Container maxWidth="sm">
        <Formik
          initialValues={{
            email: '',
            password: '',
          }}
          onSubmit={() => {
            navigate('/', { replace: true });
          }}
        >
          {({
            errors,
            handleBlur,
            handleSubmit,
            handleChange,
            isSubmitting,
            touched,
            values,
          }) => (
            <form onSubmit={handleSubmit}>
              <Typography
                color="textSecondary"
                gutterBottom
                variant="h3"
              >
                Create New Account
              </Typography>
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
                  Register
                </Button>
              </Box>
            </form>
          )}
        </Formik>
      </Container>
    </Box>
  );
}

export default Register;
