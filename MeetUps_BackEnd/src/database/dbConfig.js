const mysql = require("mysql2/promise");

const connectionPromise = mysql.createConnection({
    host: "sql5.freesqldatabase.com",
    user: "sql5517727" ,
    password: "gGVAtPjyiI",
    database: "sql5517727",
    port: 3306,
})

module.exports = {
    connectionPromise
}


