let btnCatalogue = document.querySelector('.btnCatalogue')
let btnConnexion = document.getElementById('connexion')
let divContent = document.getElementById('content')
let divTempContent = document.getElementById('tempContent')
let btnNavHome = document.getElementById('nav-home')
let modalConnexion = document.getElementById('modalConnexion')
let shadowBoxConnexion = document.querySelector('.shadowBox.connexion')
let btnFormConnexion = document.getElementById('btnFormConnexion')
let btnConnectAdmin = document.getElementById('btnConnexionAdmin')
let btnFilter
let catFilter
let catFilterValue
let token
const xhr = new XMLHttpRequest()


function getHome(){
    location.replace("/")
}
function supcontent(){
    divTempContent.remove()
}
function addCatalogue(){
    supcontent()
    if (catFilter == null){
        catFilterValue = "all"
    }else {
        catFilterValue = catFilter.value
    }
    xhr.open("GET","/getCatalogue/"+catFilterValue)
    xhr.send()
    xhr.onload=()=>{
        if (xhr.readyState == 4 && xhr.status ==200){
            divContent.innerHTML+=xhr.response
            document.title = "Mercadona - Catalogue - "+catFilterValue
            divTempContent = document.getElementById('tempContent')
            btnFilter = document.getElementById('btnFilter')
            catFilter = document.getElementById('catFilter')
            catFilter.value = catFilterValue
            btnFilter.addEventListener('click',addCatalogue)
        }
    }
}

function modalConnexionOn(){
    modalConnexion.style.display='block'
}
function modalConnexionOff(){
    modalConnexion.style.display='none'
}

function connexion(){
    xhr.open("POST","/postConnect")
    xhr.setRequestHeader("Content-Type", "application/json")
    const body = JSON.stringify({
        "id": document.getElementById('identifiant').value,
        "password": document.getElementById('password').value
    })
    xhr.send(body)
    xhr.onload=()=>{
        if (xhr.readyState == 4 && xhr.status ==200){
            token = JSON.parse(xhr.response)
            if (token["tokenId"] != "null") {
                if (token["role"] === "admin"){
                    getAdminArea()
                }
            }else {
                alert("Identifiant ou mot de pass incorrecte")
            }
        }
    }
}
/*Présent pour test par client A supprimer*/
function connexionAdmin(){
    document.getElementById('identifiant').value = "johndupont@email.fr"
    document.getElementById('password').value = "passJohnHash"
}
function getAdminArea(){
    location.replace("/adminArea?tokenId="+token["tokenId"]+"&userName="+document.getElementById('identifiant').value)
}


btnFormConnexion.addEventListener('click',connexion)
btnConnectAdmin.addEventListener('click',connexionAdmin)
shadowBoxConnexion.addEventListener('click',modalConnexionOff)
btnCatalogue.addEventListener('click',addCatalogue)
btnConnexion.addEventListener('click',modalConnexionOn)
btnNavHome.addEventListener('click',getHome)



