const express = require("express")
const { getPublicUser } = require("../database/publicUserTable")

const profileRoute = express.Router()

profileRoute.get("/", async (req, res) =>{
    try{
        const {idUser} = req.query

        if(!idUser){
            res.statusCode = 400
            res.statusMessage = "idUser manquant"
            res.end()
            return
        }

        const result = await getPublicUser(idUser)

        if(!result){
            res.statusCode = 400
            res.statusMessage = "public user n'existe pas dans la base de donnee"
            res.end()
            return
        }

        res.statusCode = 200
        res.json(result)

    } catch(e){
        res.statusCode = 400
        res.statusMessage = e
        res.end()
    }
})

module.exports = { profileRoute }