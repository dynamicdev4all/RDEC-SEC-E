const e = require("express");
const userRouter = e.Router();
const path = require("path");
const { userRegister, userLogin, userVerification } = require("../controllers/userController");

userRouter.get("/login", (request, response) => {
    response.sendFile(path.join(__dirname, "../public", "login.html"));
  });
  userRouter.get("/register", (request, response) => {
    response.sendFile(path.join(__dirname, "../public", "register.html"));
  });
  userRouter.post("/create-new-user", userRegister);  
  userRouter.post("/login-user", userLogin); 
  userRouter.get("/verify", (request, response)=>{
    console.log("Rec Token")
    console.log(request.query.token)
    userVerification(request.query.token)
  }) 

//vgwk yfqu wksm bbhh



  module.exports = userRouter;