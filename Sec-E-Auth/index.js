const e = require("express");

const server = e();
const path = require("path");
server.use(e.urlencoded({ extended: true }));

// "/" is called root route or home route
server.get("/", (request, response) => {
  //   console.log("Hey I am the root route");
  // C:\Users\ACER\Documents\Sec-E-Auth\public\homepage.html
  response.sendFile(path.join(__dirname, "public", "homepage.html"));
});
server.get("/login", (request, response) => {
  //   console.log("Hey I am the login route");
  response.sendFile(path.join(__dirname, "public", "login.html"));
});
server.get("/register", (request, response) => {
  //   console.log("Hey I am the register route");
  response.sendFile(path.join(__dirname, "public", "register.html"));
});
server.post("/create-new-user", (request, response) => {
  console.log("Hey I am the new user route");
  console.log(request.body);
});

server.listen(1212, () => {
  console.log("Server is running on port 1212");
});
