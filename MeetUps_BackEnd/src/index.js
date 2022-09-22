const express = require("express")
const { availableProfile } = require("./routes/availableProfileRoute")
const { likeProfileRoute } = require("./routes/likeProfileRoute")
const { loginRoute } = require("./routes/loginRoute")
const { matchRoute } = require("./routes/matchRoute")
const { photoRoute } = require("./routes/photoRoute")
const { profileRoute } = require("./routes/profleRoute")
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
app.use("/likeProfile", likeProfileRoute)
app.use("/myMatch", matchRoute)
app.use("/profile", profileRoute)
app.use("/photo", photoRoute)

app.listen(port, ()=> console.log(`App listening on port ${port}`))

exports.app = app