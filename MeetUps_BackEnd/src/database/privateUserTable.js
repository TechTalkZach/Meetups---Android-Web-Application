
const {connectionPromise} = require("./dbConfig")

const TABLE_NAME = "privateUser"
const COLUMN_COURRIEL = "courriel"
const COLUMN_MOTDEPASSE = "motDePasse"

 async function insertPrivateUser(courriel, motDePasse){
    try {
        const connection = await connectionPromise
        const [row, fields] = await connection.query(
            `INSERT INTO privateUser (${COLUMN_COURRIEL}, ${COLUMN_MOTDEPASSE}) VALUES (?, ?)`,
            [courriel, motDePasse]
        )

       return { privateUserID : row.insertId }
       

    } catch (e) {
        let errorMessage;

        if(e.code === "ER_DUP_ENTRY")
            errorMessage = "Un utilisateur a deja ete creer avec ce courriel"
        else
           // errorMessage = "erreur dans l'enregistrement" + e.message;
			errorMessage = e.message;

        throw errorMessage
    }
}

async function getPrivateUser(courriel){
    try{
        const connection = await connectionPromise
        const query = `SELECT * FROM ${TABLE_NAME} WHERE ${COLUMN_COURRIEL} = ?;`

        const [row] = await connection.query(query, [courriel])

        if(row.length === 0)
            return null
        else
            return row[0]

    } catch(e){
       // throw "Erreur lors de la connexion"+ e.message;
	   throw e;
    }
}

module.exports = {
    insertPrivateUser,
    getPrivateUser
}

