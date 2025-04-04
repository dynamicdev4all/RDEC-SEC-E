const e = require("express");
const server = e();
const dotenv = require('dotenv').config();
const userRouter = require("./routes/userRoutes");
const appRouter = require("./routes/appRoutes");
const dbConn = require("./config/databaseConnection");
server.use(e.urlencoded({ extended: true }));

server.use("/",appRouter)
server.use("/user",userRouter)

const PORT = process.env.PORT;

server.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
