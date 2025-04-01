const e = require("express");
const appRouter = e.Router();
const path = require("path");

appRouter.get("/", (request, response) => {
    response.sendFile(path.join(__dirname, "../public", "homepage.html"));
  });

  module.exports = appRouter;