const express = require("express")
const { getPrivateUser } = require("../database/privateUserTable")


const loginRoute = express.Router()

loginRoute.post("/", async (req, res) => {
    try{
        const {courriel, motDePasse} = req.body

        const privateUser = await getPrivateUser(courriel)

        if(privateUser === null){
            res.statusCode = 400
            res.statusMessage = "Verifier le courriel"
            res.end()
            return
        }

        if(privateUser.motDePasse !== motDePasse){
            res.statusCode = 400
            res.statusMessage = "Verifier le mot de passe"
            res.end()
            return
        }

        res.statusCode = 200
        res.statusMessage = "Login successful"
        res.json({idUser : privateUser.idUser})

    } catch (errorMessage){
        res.statusCode = 400
        res.statusMessage = errorMessage
        res.end()

    }
})

module.exports = {loginRoute}