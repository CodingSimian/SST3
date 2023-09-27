module.exports = errorHandler;
const getLogger = require("../extra/logger");
const logger = getLogger();

function errorHandler(err, req, res, next) {
  switch (true) {
    case typeof err === "string":
      console.log("in error handler");
      // custom application error
      const is404 = err.toLowerCase().endsWith("not found");
      const statusCode = is404 ? 404 : 400;
      return res.status(statusCode).json({ message: err });
    default:
      return res.status(500).json({ message: err.message });
  }
}
