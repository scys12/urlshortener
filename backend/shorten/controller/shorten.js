import AppError from '../errors/AppError.js';
import Util from '../util.js';
import Shorten from '../models/shorten.js';

const getShortUrl = async (req, res) => {
  const { shortUrl } = req.params;
  const url = await Shorten.findOne({ shortUrl });
  const now = new Date();
  if (url && (now <= url.expirationDate)) {
    res.redirect(url.pasteContent);
  } else {
    throw new AppError('Url not exist', 403);
  }
};

const createNewShortener = async (req, res) => {
  const pasteContent = req.body.paste_content;
  const expireDate = req.body.expire_date;
  const { type } = req.body;
  const date = new Date();
  let shortUrl;
  const expirationDate = !expireDate ? date.setFullYear(date.getFullYear() + 5) : expireDate;
  if (type === 'custom') {
    shortUrl = req.body.custom_link;
  } else if (type === 'short') {
    const ipAddr = req.headers['x-forwarded-for'] || req.ip;
    const hashData = ipAddr + pasteContent;
    shortUrl = Util.encodeNewUrl(hashData);
  }
  let shorten = await Shorten.findOne({ shortUrl }).select('-updatedAt -type -__v');
  if (!shorten) {
    const newShorten = await Shorten.create({
      shortUrl, expirationDate, pasteContent, type,
    });
    if (newShorten) {
      shorten = {
        // eslint-disable-next-line no-underscore-dangle
        _id: newShorten._id,
        shortUrl: newShorten.shortUrl,
        expirationDate: newShorten.expirationDate,
        pasteContent: newShorten.pasteContent,
        createdAt: newShorten.createdAt,
      };
    } else {
      throw new AppError('An error occured', 500);
    }
  } else shorten = shorten.toJSON();
  res.status(200).json({
    message: 'Short link created',
    data: shorten,
  });
};

export default {
  createNewShortener,
  getShortUrl,
};
