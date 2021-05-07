/* eslint-disable import/prefer-default-export */
import { validationResult } from 'express-validator';
import AppError from '../errors/AppError.js';

// eslint-disable-next-line no-unused-vars
export const errorHandler = (err, req, res, next) => {
  if (err instanceof AppError) {
    res.status(err.status).send(err.response());
  } else if (err) {
    res.status(500).json({ error: 'An error occurred.' });
  }
};

export const verify = (req, res, next) => {
  const errors = validationResult(req);
  if (!errors.isEmpty()) {
    const message = errors.array()[0].msg;
    throw new AppError(message, 400);
  }
  next();
};
