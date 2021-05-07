import mongoose from 'mongoose';

const shortenSchema = mongoose.Schema(
  {
    shortUrl: {
      type: String,
      required: true,
      unique: true,
    },
    expirationDate: {
      type: Date,
      required: true,
    },
    pasteContent: {
      type: String,
      required: true,
    },
    type: {
      type: String,
      required: true,
    },
  },
  {
    timestamps: true,
  },
);

const Shorten = mongoose.model('shortens', shortenSchema);
export default Shorten;
