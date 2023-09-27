const config = require("../config.json");
const mysql = require("mysql2/promise");
const { Sequelize } = require("sequelize");

module.exports = db = {};

initialize();

async function initialize() {
  // loads database properties from config file
  const { host, port, user, password, database } = config.database;

  // connect to db
  const sequelize = new Sequelize(database, user, password, {
    dialect: "mysql",
  });

  // init models and add them to exported db objects
  db.User = require("../models/user")(sequelize);

  // sync all models with database
  await sequelize.sync({ alter: true });
}
