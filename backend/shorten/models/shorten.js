import mongoose from 'mongoose';

const shortenSchema = mongoose.Schema(
  {
    shortUrl: {
      type: String,
      required: true,
      unique: true,
    },
    expiration_length_in_minutes: {
      type: Number,
      required: true,
    },
    paste_content: {
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
