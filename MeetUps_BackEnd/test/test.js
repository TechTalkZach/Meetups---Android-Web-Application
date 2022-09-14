const { getPrivateUser } = require('../src/database/privateUserTable');

const fetch = (...args) => import('node-fetch').then(({default: fetch}) => fetch(...args));

const url = "https://meetups01.herokuapp.com"
const testURL = "http://localhost:4000"

const privateUser = {
    courriel: "caven.kathiresu5@gmail.com",
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
    prenom : "kathiresu"
}

async function test1(){
    const response = await getPrivateUser("caven")
    console.log(response)
}

async function test(){
    const options = {
        method : "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
        body: JSON.stringify(privateUser) 
    }

    const response = await fetch(url + "/login", options)

    console.log(response.status)

    if(response.status === 200)
        console.log(await response.json())

    console.log(response.statusText)


}

test()