import { configureStore } from '@reduxjs/toolkit';
import logger from 'redux-logger';
import persistStore from 'redux-persist/es/persistStore';
import rootReducers from '../reducers';

const newStore = () => configureStore({
  reducer: rootReducers,
  middleware: (getDefaultMiddleware) => getDefaultMiddleware({
    serializableCheck: false,
  }).concat(logger),
});

const store = newStore();

export const persistor = persistStore(store);

export default store;
