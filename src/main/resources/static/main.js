let btnCatalogue = document.querySelector('.btnCatalogue')
let btnConnexion = document.getElementById('connexion')
let divContent = document.getElementById('content')
let divTempContent = document.getElementById('tempContent')
let btnNavHome = document.getElementById('nav-home')
let btnFilter
let catFilter
let catFilterValue
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

function connexion(){
    alert("Fonction connexion à implémenter")
}

btnCatalogue.addEventListener('click',addCatalogue)
btnConnexion.addEventListener('click',connexion)
btnNavHome.addEventListener('click',getHome)



