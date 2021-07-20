import { combineReducers } from 'redux';
import storage from 'redux-persist/lib/storage';
import { persistReducer } from 'redux-persist';
import authReducer from './authReducer';

const rootPersistConfig = {
  key: 'root',
  storage,
  whitelist: ['auth'],
};

const reducer = combineReducers({
  auth: authReducer,
});

const rootReducers = persistReducer(rootPersistConfig, reducer);

export default rootReducers;
