const { connectionPromise } = require("./dbConfig")

const TABLE_NAME = "publicUser"
const COLUMN_ID = "id"
const COLUMN_NOM = "nom"
const COLUMN_SEXE = "sexe"
const COLUMN_AGE = "age"
const COLUMN_GRANDEUR = "grandeur"
const COLUMN_EDUCATION = "education"
const COLUMN_SITUATIONFAMILIALE = "situationFamiliale"
const COLUMN_RELIGION = "religion"
const COLUMN_RECHERCHE = "recherche"
const COLUMN_PRENOM = "prenom"
const COLUMN_PHOTOPROFILURL = "photoProfilURL"

async function insertPublicUser(id, {nom, sexe, age, grandeur, education, situationFamiliale, religion, recherche, prenom, photoProfilURL}){
    try{

        const connection = await connectionPromise
        const query = `INSERT INTO ${TABLE_NAME} 
            (
                ${COLUMN_ID}, 
                ${COLUMN_NOM}, 
                ${COLUMN_SEXE}, 
                ${COLUMN_AGE}, 
                ${COLUMN_GRANDEUR}, 
                ${COLUMN_EDUCATION}, 
                ${COLUMN_SITUATIONFAMILIALE}, 
                ${COLUMN_RELIGION}, 
                ${COLUMN_RECHERCHE}, 
                ${COLUMN_PRENOM},
                ${COLUMN_PHOTOPROFILURL}
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`
        const [row, fields] = await connection.query(
            query,
            [
                id,
                nom,
                sexe, 
                age,
                grandeur,
                education,
                situationFamiliale,
                religion,
                recherche,
                prenom,
                photoProfilURL
            ]
        )

    } catch (e){
        throw "Erreur dans la sauvegarde des informations publics"
    }
    
}

async function getPublicUser(idUser){
    try{
        const query = `
            SELECT *
            FROM ${TABLE_NAME}
            WHERE ${COLUMN_ID} = ?
        `

        const connection = await connectionPromise
        const [row] = await connection.query(query, [idUser])

        if(row.length === 0)
            return null
        
        return row[0]

    } catch (e){
        throw "Erreur lors de l'obtention du public user"
    }
}



module.exports = {
    TABLE_NAME,
    COLUMN_ID,
    insertPublicUser,
    getPublicUser
}