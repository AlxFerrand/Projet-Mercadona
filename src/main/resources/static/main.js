let btnCatalogue = document.querySelector('.btnCatalogue')
let btnConnexion = document.getElementById('connexion')
let divContent = document.getElementById('content')
let divTempContent = document.getElementById('tempContent')
let btnNavHome = document.getElementById('nav-home')

function getHome(){
    location.replace("/")
}
function supcontent(){
    divTempContent.remove()
}
function addCatalogue(){
    supcontent()
    alert("requete vers controller a implémenter")
    let result = '<h1>Contenue Catalogue</h1>'
    divContent.innerHTML+=result
}
function connexion(){
    alert("Fonction connexion à implémenter")
}


btnCatalogue.addEventListener('click',addCatalogue)
btnConnexion.addEventListener('click',connexion)
btnNavHome.addEventListener('click',getHome)



