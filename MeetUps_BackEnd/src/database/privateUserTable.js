
const {connectionPromise} = require("./dbConfig")

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
            errorMessage = "erreur dans l'enregistrement"

        throw errorMessage
    }
}

module.exports = {
    insertPrivateUser
}

