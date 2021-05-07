import express from 'express';
import { check } from 'express-validator';
import asyncHandler from 'express-async-handler';
import Shorten from '../controller/shorten.js';
import { verify } from '../middleware/index.js';

const routes = express.Router();

routes.get(
  '/:shortUrl',
  asyncHandler(Shorten.getShortUrl),
);

routes.post(
  '/',
  check('paste_content')
    .exists({ checkNull: true, checkFalsy: true })
    .withMessage('Target is missing.')
    .isString()
    .trim()
    .isLength({ min: 1, max: 2040 })
    .withMessage('Maximum URL length is 2040.')
    .isURL({ protocols: ['http', 'https'], require_protocol: true })
    .withMessage('Please enter valid URL'),
  check('expire_date')
    .optional({ nullable: true, checkFalsy: true })
    .isString()
    .trim()
    .isDate()
    .withMessage('Please enter valid date'),
  check('type')
    .exists({ checkFalsy: true, checkNull: true })
    .withMessage('No type shorten link')
    .isString()
    .trim()
    .isIn(['custom', 'short', 'long'])
    .withMessage('Please enter the right type'),
  check('custom_link')
    .optional({ checkFalsy: true, nullable: true })
    .isString()
    .trim()
    .isLength({ min: 5, max: 50 })
    .withMessage('Maximum custom link length is 50')
    .custom((value) => /^[a-zA-Z0-9-_]+$/g.test(value))
    .withMessage('Custom URL is not valid')
    .withMessage("You can't use this custom link."),
  asyncHandler(verify),
  asyncHandler(Shorten.createNewShortener),
);
export default routes;
