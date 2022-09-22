const { connectionPromise } = require("./dbConfig")

const TABLE_NAME = "photo"
const COLUMN_IDPHOTO = "idPhoto"
const COLUMN_PHOTOURL = "photoURL"
const COLUMN_PUBLICUSERID = "publicUserId"

async function getPhotosForProfile(idUser){
    try{
        const query = `
            SELECT * 
            FROM ${TABLE_NAME}
            WHERE ${COLUMN_PUBLICUSERID} = ?
        `

        const connection = await connectionPromise
        const [row] = await connection.query(query, [idUser])

        return row

    } catch(e){
        throw "Erreur lors de l'obtention des photos"
    }
}

module.exports = { getPhotosForProfile }