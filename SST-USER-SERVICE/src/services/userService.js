const db = require("../database/mysqlDB");

module.exports = {
  getAllUsers,
  getById,
  createNewUser,
  update,
  delete: _delete,
};

async function getAllUsers() {
  return await db.User.findAll();
}

async function getById(id) {
  return await getUser(id);
}

async function createNewUser(params) {
  // validate
  console.log("In userService");
  console.log(params);
  if (await db.User.findOne({ where: { email: params.email } })) {
    console.log("test");
    throw 'Email"' + params.email + '" is already registered';
  } else if (
    await db.User.findOne({ where: { user_name: params.user_name } })
  ) {
    throw 'Username"' + params.user_name + '" is already registered';
  }

  const user = new db.User(params);

  await user.save();
  return user.id;
}

async function update(id, params) {
  const user = await getUser(id);

  // validate
  const usernameChanged = params.username && user.username !== params.username;
  if (
    usernameChanged &&
    (await db.User.findOne({ where: { username: params.username } }))
  ) {
    throw 'Username"' + params.username + '" is already taken';
  }

  Object.assign(user, params);
  await user.save();
}

async function _delete(id) {
  const user = await getUser(id);
  await user.destroy();
}

// helper function
async function getUser(id) {
  const user = await db.User.findByPk(id);
  if (!user) throw "User not found";
  return user;
}
