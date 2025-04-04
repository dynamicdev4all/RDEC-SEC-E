const e = require("express");
const userRouter = e.Router();
const path = require("path");
const { userRegister, userLogin } = require("../controllers/userController");

userRouter.get("/login", (request, response) => {
    response.sendFile(path.join(__dirname, "../public", "login.html"));
  });
  userRouter.get("/register", (request, response) => {
    response.sendFile(path.join(__dirname, "../public", "register.html"));
  });
  userRouter.post("/create-new-user", userRegister);  
  userRouter.post("/login-user", userLogin);  

//vgwk yfqu wksm bbhh



  module.exports = userRouter;