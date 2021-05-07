import express from 'express';
import dotenv from 'dotenv';
import morgan from 'morgan';
import connectToMongoDb from './config/db.js';
import routes from './routes/routes.js';

dotenv.config();
const app = express();
connectToMongoDb();
app.use(express.json());
app.use(morgan('dev'));
app.use(routes);

app.all('*', async (req, res) => res.status(404).json({ message: 'Request not found' }));

const port = process.env.PORT || 5000;
app.listen(port, () => {});
