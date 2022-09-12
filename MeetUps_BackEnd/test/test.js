
const fetch = (...args) => import('node-fetch').then(({default: fetch}) => fetch(...args));

const url = "https://meetups01.herokuapp.com"

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
    console.log(await fetch(url))
}

async function test(){
    const options = {
        method : "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
        body: JSON.stringify({publicUser, privateUser}) 
    }

    const response = await fetch(url + "/register", options)

    console.log(response.status)

    if(response.status === 200)
        console.log(await response.json())

    console.log(response.statusText)


}

test()