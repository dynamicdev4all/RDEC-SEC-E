const e = require("express");
const mongoose = require("mongoose")
const server = e();
const userRouter = require("./routes/userRoutes");
const appRouter = require("./routes/appRoutes");
const dbConn = require("./config/databaseConnection");
server.use(e.urlencoded({ extended: true }));

// appRouter.use("/api")
// userRouter.use("/user")
server.use("/api",appRouter)
server.use("/user",userRouter)



server.listen(1212, () => {
  console.log("Server is running on port 1212");
});
