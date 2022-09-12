const express = require("express")
const { registerRoute } = require("./routes/registerRoute")


const app = express()
const port = process.env.port || 4000

app.use(express.json())

app.get("/", (req, res)=>{
    res.send("Meetups API")
})

app.use("/register", registerRoute)

app.listen(port, ()=> console.log(`App listening on port ${port}`))

exports.app = app