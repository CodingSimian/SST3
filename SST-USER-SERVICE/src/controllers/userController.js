// UserController

const Joi = require("joi");
const validateRequest = require("../middleware/validate-request");
const userService = require("../services/userService");
const userServiceV2 = require("../services/userService");
const getLogger = require("../extra/logger");
const logger = getLogger();

const getAllUsers = async (req, res, next) => {
  //const allUsers = await userService.getAllUsers();
  logger.info("fetching all users");
  userServiceV2
    .getAllUsers()
    .then((users) => res.json(users))
    .catch(next);
  //const allUsers = await userServiceV2.getAllUsers();
  //res.status(200).send({ status: "OK", data: allUsers });
};

const getUser = async (req, res, next) => {
  userServiceV2
    .getById(req.params.id)
    .then((user) => res.json(user))
    .catch(next);
};

const createNewUser = async (req, res, next) => {
  console.log(req.first_name);
  userServiceV2
    .createNewUser(req.body)
    .then((id) => res.json({ message: "User created with ID:" + id }))
    .catch(next);
};

const updateUser = async (req, res, next) => {
  userServiceV2
    .update(req.params.id, req.body)
    .then(() => res.json({ message: "User updated" }))
    .catch(next);
};

const deleteUser = async (req, res, next) => {
  userServiceV2
    .delete(req.params.id)
    .then(() => res.json({ message: "User deleted" }))
    .catch(next);
};

// Used to validate the params sent in by user when trying to create a new user in DB
function createSchema(req, res, next) {
  const schema = Joi.object({
    userName: Joi.string().required,
    firstName: Joi.string().required,
    birthDate: Joi.string().required,
    email: Joi.string().required,
  });
  validateRequest(req, next, schema);
}

// Same as above but used when updating an existing user instead of creating
function updateSchema(req, res, next) {
  const schema = Joi.object({
    user_name: Joi.string().empty(""),
    first_name: Joi.string().empty(""),
    birth_Date: Joi.string().empty(""),
    email: Joi.string().empty(""),
  });
  validateRequest(req, next, schema);
}

module.exports = {
  getAllUsers,
  getUser,
  createNewUser,
  updateUser,
  deleteUser,
  createSchema,
  updateSchema,
};
