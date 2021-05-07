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

shortenSchema.methods.getPublicFields = function getPublicFields() {
  const returnObject = {
    id: this.id,
    shortUrl: this.shortUrl,
    expirationDate: this.expirationDate,
    pasteContent: this.pasteContent,
  };
  return returnObject;
};

const Shorten = mongoose.model('shortens', shortenSchema);
export default Shorten;
