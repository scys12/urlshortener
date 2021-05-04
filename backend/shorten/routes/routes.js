import express from 'express';
import asyncHandler from 'express-async-handler';
import Util from '../util';
import AppError from '../errors/AppError';
import Shorten from '../models/shorten';
const router = express.Router();
router.post('/', asyncHandler(async(req, res) => {
  const {longUrl} = req.body;
  const timestamp = Date();
  const ipAddr = req.headers['x-forwarded-for'] || req.ip;
  const hashData = ipAddr + timestamp;
  let shortUrl = Util.encodeNewUrl(hashData);
  let shorten = await Shorten.findOne({ shortUrl });
  while (shorten) {
    shortUrl = Util.encodeNewUrl(hashData);
    shorten = await Shorten.findOne({ shortUrl });
  }
}));
