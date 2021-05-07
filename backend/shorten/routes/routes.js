import express from 'express';
import asyncHandler from 'express-async-handler';
import Util from '../util.js';
import Shorten from '../models/shorten.js';
import AppError from '../errors/AppError.js';

const routes = express.Router();

routes.get('/:shortUrl', asyncHandler(async (req, res) => {
  const { shortUrl } = req.params;
  const url = await Shorten.findOne({ shortUrl });
  const now = new Date();
  if (url && (now <= url.expirationDate)) {
    res.redirect(url.pasteContent);
  } else {
    throw new AppError('Url not exist', 403);
  }
}));

routes.post('/', asyncHandler(async (req, res) => {
  const pasteContent = req.body.paste_content;
  const expireDate = req.body.expire_date;
  const type = 'short';
  const date = new Date();
  const expirationDate = !expireDate ? date.setFullYear(date.getFullYear() + 5) : expireDate;
  const ipAddr = req.headers['x-forwarded-for'] || req.ip;
  const hashData = ipAddr + pasteContent;
  let shortUrl = Util.encodeNewUrl(hashData);
  const shorten = await Shorten.findOne({ shortUrl });
  if (!shorten) {
    await Shorten.create({
      shortUrl, expirationDate, pasteContent, type,
    });
  } else shortUrl = shorten.shortUrl;
  res.json({
    message: 'Short link created',
    data: {
      url: shortUrl,
    },
  });
}));
export default routes;
