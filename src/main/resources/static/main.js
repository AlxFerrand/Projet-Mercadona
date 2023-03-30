let btnCatalogue = document.querySelector('.btnCatalogue')
let btnConnexion = document.getElementById('connexion')
let divContent = document.getElementById('content')
let divTempContent = document.getElementById('tempContent')
let btnNavHome = document.getElementById('nav-home')
const xhr = new XMLHttpRequest()

function getHome(){
    location.replace("/")
}
function supcontent(){
    divTempContent.remove()
}
function addCatalogue(){
    supcontent()
    xhr.open("GET","/getCatalogue")
    xhr.send()
    xhr.onload=()=>{
        if (xhr.readyState == 4 && xhr.status ==200){
            divContent.innerHTML+=xhr.response
            document.title = "Mercadona - Catalogue"
        }
    }
}
function connexion(){
    alert("Fonction connexion à implémenter")
}

btnCatalogue.addEventListener('click',addCatalogue)
btnConnexion.addEventListener('click',connexion)
btnNavHome.addEventListener('click',getHome)



