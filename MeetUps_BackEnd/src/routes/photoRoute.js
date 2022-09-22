
const express = require("express")
const { getPhotosForProfile } = require("../database/photoTable")

const photoRoute = express.Router()

photoRoute.get("/", async (req, res) => {
    try{

        const {idUser} = req.query
        
        if(!idUser){
            res.statusCode = 400
            res.statusMessage = "idUser manquant"
            res.end()
            return
        }
        
        const result = await getPhotosForProfile(idUser)
        
        res.statusCode = 200
        res.json(result)

    } catch (errorMessage){
        res.statusCode = 400
        res.statusMessage = errorMessage
        res.end()
    }
})

module.exports = { photoRoute }