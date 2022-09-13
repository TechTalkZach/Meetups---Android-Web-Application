const express = require("express")
const { insertPrivateUser } = require("../database/privateUserTable")
const { insertPublicUser } = require("../database/publicUserTable")

const registerRoute = express.Router()

registerRoute.post("/", async (req, res) => {
    try{
        const {privateUser, publicUser} = req.body
        const {privateUserID} = await insertPrivateUser(privateUser.courriel, privateUser.motDePasse)

        await insertPublicUser(privateUserID, publicUser)

        res.statusCode = 200
        res.statusMessage = "Nouveau compte creer avec succes"
        res.end()

    } catch (errorMessage){
        res.statusCode = 400
        res.statusMessage = errorMessage
        res.end()
    }
    
})

module.exports = { registerRoute }