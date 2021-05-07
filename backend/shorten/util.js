import crypto from 'crypto';

const Base62Encoder = {
  base62Dict: '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ',
  encodeRawToBase10: (rawData) => {
    const rawArr = rawData.substring(0, 10);
    const dataIntBase10 = parseInt(rawArr, 16);
    return dataIntBase10;
  },
  encodeBase10To62: (base10, base = 62) => {
    let numberBase10 = base10;
    let digits = [];
    while (numberBase10 > 0) {
      const remainder = numberBase10 % base;
      digits.push(Base62Encoder.base62Dict.charAt(remainder));
      numberBase10 = Math.floor(numberBase10 / base);
    }
    digits = digits.reverse();
    return digits.join('');
  },
};

const encodeNewUrl = (rawData, base = 62) => {
  const md5sum = crypto.createHash('md5');
  const hashedData = md5sum.update(rawData).digest('hex');
  const base10Url = Base62Encoder.encodeRawToBase10(hashedData);
  const shortUrl = Base62Encoder.encodeBase10To62(base10Url, base);
  return shortUrl;
};

export default {
  Base62Encoder,
  encodeNewUrl,
};
