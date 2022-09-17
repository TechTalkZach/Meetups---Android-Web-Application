const express = require("express")
const { insertLikes } = require("../database/likesTable")

const likeProfileRoute = express.Router()

likeProfileRoute.post("/", async (req, res) => {
    try{

        const {fromIdUser, toIdUser, liked} = req.body
        
        if(!fromIdUser || !toIdUser || !liked){
            res.status = 400
            res.statusMessage = "Champs manquants pour l'insertion du like"
            res.end()
            return
        }

        await insertLikes(req.body)
        
    } catch (errorMessage){
        res.status = 400
        res.statusMessage = errorMessage
        res.end()
    }


})