const winston = require("winston");

const logger = winston.createLogger({
  level: "info",
  format: winston.format.json(),
  transports: [
    new winston.transports.File({
      filename: "combined.log",
    }),
  ],
});

function getLogger() {
  return logger;
}

module.exports = getLogger;
