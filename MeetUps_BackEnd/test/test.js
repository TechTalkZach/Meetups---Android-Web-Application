const { getPrivateUser } = require('../src/database/privateUserTable');

const fetch = (...args) => import('node-fetch').then(({default: fetch}) => fetch(...args));

const url = "https://meetups01.herokuapp.com"
const testURL = "http://localhost:4000"

const privateUser = {
    courriel: "caven.kathiresu51@gmail.com",
    motDePasse : "caven1223"
}

const publicUser = {
    nom : "caven",
    sexe : "M",
    age : 34,
    grandeur : 222,
    education : "aec",
    situationFamiliale : "en couple",
    religion : "chrétien",
    recherche : "rien",
    prenom : "kathiresu",
    photoProfilURL : "www.url.com"
}

const like = {
    fromIdUser: 15,
    toIdUser : 16,
    liked: false
}

async function test1(){
    const response = await fetch(testURL + "/profile?idUser=2")
    console.log(response.status)
    console.log(await response.text())
}

async function test(){
    const options = {
        method : "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
        body: JSON.stringify(like) 
    }

    const response = await fetch(testURL + "/likeProfile", options)

    console.log(response.status)

    // if(response.status === 200)
    //     console.log(await response.json())

    console.log(response.statusText)

}

test1()