const userModel = require("../models/userModel");
const sendMail = require("../service/sendMail");
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
const userLogin = async()=>{
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
const userVerification = ()=>{



  
}


module.exports = {userRegister, userLogin, userVerification}