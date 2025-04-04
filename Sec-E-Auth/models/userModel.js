const mongoose = require('mongoose');
const schema= mongoose.Schema({
  name:String,
  email:String,
  password:String,
  isVerified:Boolean
})

const userModel = mongoose.model("user",schema);
module.exports = userModel;

