'use strict'

const express = require("express");
console.log("APP LOADED");
const app = express();

app.use(express.json());
app.use(express.static(__dirname + "/public"));

app.post("/square", (req, res) => {
    const number = req.body.number;
    res.status(200).json(
        {
            number: number,
            square: number * number
        });
});

module.exports = app;