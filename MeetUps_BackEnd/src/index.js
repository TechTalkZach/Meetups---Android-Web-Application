const express = require("express")
const { availableProfile } = require("./routes/availableProfileRoute")
const { loginRoute } = require("./routes/loginRoute")
const { registerRoute } = require("./routes/registerRoute")


const app = express()
const port = process.env.PORT || 4000

app.use(express.json())

app.get("/", (req, res)=>{
    res.send("Meetups API")
})

app.use("/register", registerRoute)
app.use("/login", loginRoute)
app.use("/availableProfile", availableProfile)

app.listen(port, ()=> console.log(`App listening on port ${port}`))

exports.app = app