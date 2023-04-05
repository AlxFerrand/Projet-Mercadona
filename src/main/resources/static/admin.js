let navHome = document.getElementById('nav-home')
let navProducts = document.getElementById('nav-products')
let navPromo = document.getElementById('nav-promo')
let divContent = document.getElementById('content')
let divTempContent = document.getElementById('tempContent')
let btnProducts = document.getElementById('btnProducts')
let btnPromo = document.getElementById('btnPromo')
let xhr = new XMLHttpRequest

function windowLoad(){
    history.pushState({page : 1}, "EspaceAdmin", "EspaceAdmin-Menu")
    getMenu()
}
function supcontent(){
    divTempContent.remove()
}

function getMenu(){
    supcontent()
    xhr.open("GET","/getAdminMenu?tokenId="+tokenId)
    xhr.send()
    xhr.onload=()=>{
        if (xhr.readyState == 4 && xhr.status ==200){
            divContent.innerHTML+=xhr.response
            document.title = "Mercadona - Menu Admin"
            divTempContent = document.getElementById('tempContent')
            btnProducts = document.getElementById('btnProducts')
            btnPromo = document.getElementById('btnPromo')
            btnProducts.addEventListener('click',getProducts)
            btnPromo.addEventListener('click',getPromo)
            history.replaceState({page : 1}, "EspaceAdmin", "EspaceAdmin-Menu")
            navHome.classList.add('active')
            navPromo.classList.remove('active')
            navProducts.classList.remove('active')
        }
    }
}

function getProducts(){
    supcontent()
    xhr.open("GET","/getAdminProducts?tokenId="+tokenId)
    xhr.send()
    xhr.onload=()=>{
        if (xhr.readyState == 4 && xhr.status ==200){
            divContent.innerHTML+=xhr.response
            document.title = "Mercadona - Gestion Produits"
            divTempContent = document.getElementById('tempContent')
            history.pushState({page : 2}, "EspaceAdmin", "EspaceAdmin-Produits")
            navHome.classList.remove('active')
            navPromo.classList.remove('active')
            navProducts.classList.add('active')
        }
    }
}
function getPromo(){
    supcontent()
    xhr.open("GET","/getAdminPromo?tokenId="+tokenId)
    xhr.send()
    xhr.onload=()=>{
        if (xhr.readyState == 4 && xhr.status ==200){
            divContent.innerHTML+=xhr.response
            document.title = "Mercadona - Gestion Promotions"
            divTempContent = document.getElementById('tempContent')
            history.pushState({page : 3}, "EspaceAdmin", "EspaceAdmin-Promotions")
            navHome.classList.remove('active')
            navPromo.classList.add('active')
            navProducts.classList.remove('active')
        }
    }
}

window.addEventListener('load',windowLoad)
