const express = require("express")
const { getAvailablePublicUsers } = require("../database/publicUserTable")

const availableProfile = express.Router()

availableProfile.get("/", async (req, res) => {
    try{
        const {idUser} = req.query
        const result = await getAvailablePublicUsers(idUser)

        res.statusCode = 200
        res.json(result)

    } catch (errorMessage){
        res.statusCode = 400
        res.statusMessage = errorMessage
        res.end()
    }
})

module.exports = {
    availableProfile
}