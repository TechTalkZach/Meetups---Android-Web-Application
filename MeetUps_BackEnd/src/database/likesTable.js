const { connectionPromise } = require("./dbConfig")


const TABLE_NAME = "likes"
const COLUMN_FROMIDUSER = "fromIdUser"
const COLUMN_TOIDUSER = "toIdUser"
const COLUMN_LIKED = "liked"

async function insertLikes({fromIdUser, toIdUser, liked}){
    try{
        const query = `INSERT INTO ${TABLE_NAME} 
            (${COLUMN_FROMIDUSER}, ${COLUMN_TOIDUSER}, ${COLUMN_LIKED})
            VALUES (?, ?, ?)`

        const connection = await connectionPromise
        
        await connection.query(
            query,
            [fromIdUser, toIdUser, liked]
        )

    } catch (e){
        throw "Erreur lors de l'insertion du like"
    }
}

module.exports ={
    TABLE_NAME,
    COLUMN_FROMIDUSER,
    COLUMN_TOIDUSER,
    COLUMN_LIKED,
    insertLikes
}