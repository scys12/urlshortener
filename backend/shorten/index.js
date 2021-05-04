import express from 'express';
import dotenv from 'dotenv';
import morgan from 'morgan';
import connectToMongoDb from './config/db';

dotenv.config();
const app = express();
connectToMongoDb();
app.use(express.json());
app.use(morgan('dev'));
const port = process.env.PORT || 5000;
app.listen(port, () => {});
