import React from 'react';
import { Provider } from 'react-redux';
import { PersistGate } from 'redux-persist/integration/react';
import { ThemeProvider } from '@material-ui/core/styles';
import './App.css';
import { useRoutes } from 'react-router';
import store, { persistor } from './store';
import theme from './theme';
import routes from './routes/routes';

function App() {
  const routing = useRoutes(routes);
  return (
    <Provider store={store}>
      <ThemeProvider theme={theme}>
        <PersistGate loading={null} persistor={persistor}>
          {routing}
        </PersistGate>
      </ThemeProvider>
    </Provider>
  );
}

export default App;
