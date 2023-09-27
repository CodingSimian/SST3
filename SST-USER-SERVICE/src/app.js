const express = require("express");
const app = express();
//const log = require("./middleware/logger");
const bodyParser = require("body-parser"); //const db = require("./database/mysqlDB");
const v1Router = require("./routes/userRoutes");
const errorHandler = require("./middleware/error-handler");
const eurekaHelper = require("./extra/eureka-helper");
const getLogger = require("./extra/logger");

const PORT = process.env.PORT || 3000;
app.use(bodyParser.json());

const logger = getLogger();
logger.info("test från main");

// api routes
app.use("/api/users", v1Router);

// global error handler
app.use(errorHandler);

// starts server
app.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}`);
});

eurekaHelper.registerWithEureka("user-service", PORT);
