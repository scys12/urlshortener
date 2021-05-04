const errorHandler = (err, req, res, next) => {
  const resp = err.resp.data;
  res
    .status(err.status)
    .send(resp);
};

export default {
  errorHandler,
};
