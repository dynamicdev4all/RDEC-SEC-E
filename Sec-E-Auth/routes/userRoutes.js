const e = require("express");
const userRouter = e.Router();
const path = require("path");

userRouter.get("/login", (request, response) => {
    response.sendFile(path.join(__dirname, "../public", "login.html"));
  });
  userRouter.get("/register", (request, response) => {
    response.sendFile(path.join(__dirname, "../public", "register.html"));
  });
  userRouter.post("/create-new-user", async (request, response) => {
    const {username, email, password} =  request.body;
  });  
  userRouter.post("/login-user", async (request, response) => {
    const { email, password} =  request.body;
  });  


  module.exports = userRouter;