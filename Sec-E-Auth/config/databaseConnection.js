const mongoose = require("mongoose")
const MONGODB_URI=process.env.MONGODB_URI;

const dbConn = mongoose.connect(MONGODB_URI).then(()=>{
  console.log("Database Connection Success")
}).catch((err)=>{
  console.log("Issue with database ",err)
})

module.exports = dbConn;