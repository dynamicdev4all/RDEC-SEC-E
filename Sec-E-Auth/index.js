const e = require("express");
const mongoose = require("mongoose")
const server = e();
const path = require("path");
const { exit } = require("process");
server.use(e.urlencoded({ extended: true }));

mongoose.connect("mongodb+srv://piebytwo014:piebytwo014@cluster0.yw1fbph.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0").then(()=>{
  console.log("Database Connection Success")
}).catch((err)=>{
  console.log("Issue with database ",err)
})

const schema= mongoose.Schema({
  name:String,
  email:String,
  password:String,
  isVerified:Boolean
})

const userModel = mongoose.model("user",schema);

server.get("/", (request, response) => {
  response.sendFile(path.join(__dirname, "public", "homepage.html"));
});
server.get("/login", (request, response) => {
  response.sendFile(path.join(__dirname, "public", "login.html"));
});
server.get("/register", (request, response) => {
  response.sendFile(path.join(__dirname, "public", "register.html"));
});
server.post("/create-new-user", async (request, response) => {
  const {username, email, password} =  request.body;
  try {
  const exist = await userModel.findOne({email:email});
    if(exist){
      console.log("Email associated with another account")
    }
    else{
      const newUser = await new userModel({
        name:username,
        email:email,
        password:password,
        isVerified:false,
      })
      await newUser.save();
      console.log("Register Success")
    }
    }
 catch (error) {
    console.log("Register Fail ",error)
  }
});  

server.post("/login-user", async (request, response) => {
  const { email, password} =  request.body;
  try {
  const exist = await userModel.findOne({email:email});
    if(exist){
      if(exist.password == password && exist.isVerified){
        console.log("Login Success")
      }
      else if(exist.password == password && !exist.isVerified){
        console.log("Please Verify your Account")
      }
      if(exist.password != password){
        console.log("Invalid Password")
      }
    }
    else{
      console.log("User not found")
    }
    }
 catch (error) {
    console.log("Register Fail ",error)
  }
});  
server.listen(1212, () => {
  console.log("Server is running on port 1212");
});
