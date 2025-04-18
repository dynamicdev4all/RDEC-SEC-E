const userModel = require("../models/userModel");
const sendMail = require("../service/sendMail");
const jwt = require('jsonwebtoken')
const JWT_SECRET = process.env.JWT_SECRET;

const userRegister = async(request ,response)=>{
  const {username, email, password} = request.body;
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
            // await newUser.save();
            sendMail(email, username)
            console.log("Register Success")
          }
          }
       catch (error) {
          console.log("Register Fail ",error)
        }
} 
const userLogin = async(request, response)=>{
  const {email, password} = request.body;
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
}
const userVerification = async (recToken)=>{
  try {
    const tokenDecode = jwt.verify(recToken, JWT_SECRET)
    const emailToBeVerified = tokenDecode.email;
const verified =  await userModel.findOneAndUpdate(
      {email:emailToBeVerified},
      {isVerified : true}, 
      {new:true}
    )
    if(verified.isVerified){
      console.log("Verification Success")
    }else{
      console.log("Verification Failed")
    }
    console.log(emailToBeVerified)
  } catch (error) {
    if(jwt.TokenExpiredError){
      console.log("The token is expired")
    }
    console.log(error)
  }
  
}


module.exports = {userRegister, userLogin, userVerification}