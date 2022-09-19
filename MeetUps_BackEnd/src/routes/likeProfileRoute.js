const express = require("express")
const { insertLikes } = require("../database/likesTable")

const likeProfileRoute = express.Router()

likeProfileRoute.post("/", async (req, res) => {
    try{

        const {fromIdUser, toIdUser, liked} = req.body
        
        if(
            fromIdUser == undefined || 
            toIdUser == undefined || 
            liked == undefined 
        ){
            res.statusCode = 400
            res.statusMessage = "Champs manquants pour l'insertion du like"
            res.end()
            return
        }

        await insertLikes(req.body)

        res.statusCode = 200
        res.statusMessage = "Like inserer avec success"
        res.end()
        
    } catch (errorMessage){
        res.statusCode = 400
        res.statusMessage = errorMessage
        res.end()
    }
})

module.exports = {
    likeProfileRoute
}