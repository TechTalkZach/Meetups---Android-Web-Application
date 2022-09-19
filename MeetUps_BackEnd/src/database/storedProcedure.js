const {connectionPromise} = require("./dbConfig")
const likesTable = require("./likesTable")
const publicUserTable = require("./publicUserTable")

// Function to get all user profiles that the user didn't liked or dislikes
async function getAvailablePublicUsers(idUser){
    try{
        const userThatILikedQuery = `
            SELECT ${likesTable.COLUMN_TOIDUSER}
            FROM ${likesTable.TABLE_NAME}
            WHERE ${likesTable.COLUMN_FROMIDUSER} = ?
        `
        const query = `
            SELECT * FROM ${publicUserTable.TABLE_NAME}
            WHERE ${publicUserTable.COLUMN_ID} != ?
            AND ${publicUserTable.COLUMN_ID} NOT IN (${userThatILikedQuery})
        `

        const connection = await connectionPromise
        const [row] = await connection.query(query, [idUser, idUser])

        return row

    } catch (e){
        throw "Erreur lors de la recherche des profils"
    }
}

// Function to get all match for a user
async function getAllMatch(idUser){
    try{
        const connection = await connectionPromise
        const userThatLikedMeQuery = `
            SELECT ${likesTable.COLUMN_FROMIDUSER}
            FROM ${likesTable.TABLE_NAME}
            WHERE ${likesTable.COLUMN_TOIDUSER} = ?
            AND ${likesTable.COLUMN_LIKED} = true`

        const userThatILikedMatchQuery = `
            SELECT ${likesTable.COLUMN_TOIDUSER} 
            FROM ${likesTable.TABLE_NAME}
            WHERE ${likesTable.COLUMN_FROMIDUSER} = ?
            AND ${likesTable.COLUMN_LIKED} = true
            AND ${likesTable.COLUMN_TOIDUSER} IN (${userThatLikedMeQuery})`

        const query = `
            SELECT * 
            FROM ${publicUserTable.TABLE_NAME}
            WHERE ${publicUserTable.COLUMN_ID} IN (${userThatILikedMatchQuery})
        `            

       const [row] = await connection.query(query,[idUser, idUser])

       return row

    } catch (e){
        throw "Erreur lors de l'obtention des matchs"
    }
}

module.exports = {
    getAvailablePublicUsers,
    getAllMatch
}