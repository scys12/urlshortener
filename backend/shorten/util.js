import md5 from 'md5';

const Base62Encoder = {
  base62Dict: '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ',
  encodeRawToBase10: (rawData) => rawData
    .map((s) => this.base62Dict.indexOf(s)).reduce((sum, val) => sum + val, 0),
  encodeBase10To62: (base10, base = 62) => {
    let numberBase10 = base10;
    let digits = [];
    while (numberBase10 > 0) {
      const remainder = numberBase10 % base;
      digits.push(remainder);
      numberBase10 /= base;
    }
    digits = digits.reverse();
    return digits.toString();
  },
};

const encodeNewUrl = (rawData, base = 62) => {
  const hashedData = md5(rawData);
  const base10Url = Base62Encoder.encodeRawToBase10(hashedData);
  const shortUrl = Base62Encoder.encodeBase10To62(base10Url, base);
  return shortUrl;
};

export default {
  Base62Encoder,
  encodeNewUrl,
};
