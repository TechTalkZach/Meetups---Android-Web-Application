
const express = require("express")
const { getAllMatch } = require("../database/storedProcedure")


const matchRoute = express.Router()

matchRoute.get("/", async (req, res) => {
    try{
        const {idUser} = req.query

        if(!idUser){
            res.statusCode = 400
            res.statusMessage = "idUser manquant"
            res.end()
            return
        }

        const result = await getAllMatch(idUser)

        res.statusCode = 200
        res.json(result)
        res.end()

    } catch(errorMessage){
        res.statusCode = 400
        res.statusMessage = errorMessage
        res.end()
    }
})

module.exports = {
    matchRoute
}