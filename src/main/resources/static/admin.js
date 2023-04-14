let navHome = document.getElementById('nav-home')
let navProducts = document.getElementById('nav-products')
let navPromo = document.getElementById('nav-promo')
let divContent = document.getElementById('content')
let divTempContent = document.getElementById('tempContent')
let divModal = document.getElementById('modal')
let divTempModal = document.getElementById('tempModal')
let btnDeconnexion = document.getElementById('deconnexion')
let catFilter
let catFilterValue

function windowLoad(){
    history.pushState({page : 1}, "EspaceAdmin", "EspaceAdmin-Menu")
    getMenu()
}
function deconnexion(){
    location.replace("/")
}
function supcontent(){
    divTempContent.remove()
}

function supModal(){
    divTempModal.remove()
}

function getMenu(){
    supcontent()
    let xhrRouter = new XMLHttpRequest
    xhrRouter.open("GET","/getAdminMenu?tokenId="+tokenId)
    xhrRouter.send()
    xhrRouter.onload=()=>{
        if (xhrRouter.readyState == 4 && xhrRouter.status ==200){
            divContent.innerHTML+=xhrRouter.response
            document.title = "Mercadona - Menu Admin"
            divTempContent = document.getElementById('tempContent')
            document.getElementById('btnProducts').addEventListener('click',getProducts)
            document.getElementById('btnPromo').addEventListener('click',getPromo)
            history.replaceState({page : 1}, "EspaceAdmin", "EspaceAdmin-Menu")
            navHome.classList.add('active')
            navPromo.classList.remove('active')
            navProducts.classList.remove('active')
        }else {
            alert("connexion avec le serveur échouée")
        }
    }
}

function getProducts(){
    supcontent()
    if (catFilter == null){
        catFilterValue = "all"
    }else {
        catFilterValue = catFilter.value
    }
    let xhrRouter = new XMLHttpRequest
    xhrRouter.open("GET","/getAdminProducts/"+catFilterValue+"?tokenId="+tokenId)
    xhrRouter.send()
    xhrRouter.onload=()=>{
        if (xhrRouter.readyState == 4 && xhrRouter.status ==200){
            divContent.innerHTML+=xhrRouter.response
            document.title = "Mercadona - Gestion Produits"
            divTempContent = document.getElementById('tempContent')
            document.getElementById('btnModalAdd').addEventListener('click',getModalAdd)
            history.pushState({page : 2}, "EspaceAdmin", "EspaceAdmin-Produits")
            document.getElementById('btnFilter').addEventListener('click',getProducts)
            catFilter = document.getElementById('catFilter')
            catFilter.value = catFilterValue
            navHome.classList.remove('active')
            navPromo.classList.remove('active')
            navProducts.classList.add('active')
        }else {
            alert("connexion avec le serveur échouée")
        }
    }
}
function getPromo(){
    supcontent()
    if (catFilter == null){
        catFilterValue = "all"
    }else {
        catFilterValue = catFilter.value
    }
    let xhrRouter = new XMLHttpRequest()
    xhrRouter.open("GET","/getAdminPromo/"+catFilterValue+"?tokenId="+tokenId)
    xhrRouter.send()
    xhrRouter.onload=()=>{
        if (xhrRouter.readyState == 4 && xhrRouter.status ==200){
            divContent.innerHTML+=xhrRouter.response
            document.title = "Mercadona - Gestion Promotions"
            divTempContent = document.getElementById('tempContent')
            history.pushState({page : 3}, "EspaceAdmin", "EspaceAdmin-Promotions")
            document.getElementById('btnFilter').addEventListener('click',getPromo)
            catFilter = document.getElementById('catFilterPromo')
            catFilter.value = catFilterValue
            navHome.classList.remove('active')
            navPromo.classList.add('active')
            navProducts.classList.remove('active')
        }else {
            alert("connexion avec le serveur échouée")
        }
    }
}
function getModalAdd(){
    supModal()
    let xhrRouter = new XMLHttpRequest()
    xhrRouter.open("GET","/getAddModale?tokenId="+tokenId)
    xhrRouter.send()
    xhrRouter.onload=()=> {
        if (xhrRouter.readyState == 4 && xhrRouter.status == 200) {
            divModal.innerHTML += xhrRouter.response
            divTempModal = document.getElementById('tempModal')
            let formAdd= document.getElementById('formAdd')
            /**/
            formAdd.addEventListener('submit',function (e){
                e.preventDefault();
                const xhrService = new XMLHttpRequest()
                let data = new FormData(formAdd)
                xhrService.onload=()=> {
                    if (xhrService.readyState == 4 && xhrService.status == 200) {
                        if (xhrService.responseText.startsWith("Erreur",0)) {
                            alert(xhrService.responseText)
                        }else {
                            alert(xhrService.responseText)
                            supModal()
                            getProducts()
                        }
                    }else {
                        alert("connexion avec le serveur échouée")
                    }
                }
                xhrService.open("POST","/postAddProduct?tokenId="+tokenId)
                xhrService.send(data)
            })
            document.getElementById('btnCancel').addEventListener('click', supModal)
            document.querySelector('.shadowBox').addEventListener('click',supModal)
            history.replaceState({page: 2}, "EspaceAdmin-Produits", "EspaceAdmin-Produits")
        }else {
            alert("connexion avec le serveur échouée")
        }
    }
}
function getModalUpdate(productId){
    supModal()
    let xhrRouter = new XMLHttpRequest()
    xhrRouter.open("GET","/getUpdateModale/"+productId+"?tokenId="+tokenId)
    xhrRouter.send()
    xhrRouter.onload=()=> {
        if (xhrRouter.readyState == 4 && xhrRouter.status == 200) {
            divModal.innerHTML += xhrRouter.response
            divTempModal = document.getElementById('tempModal')
            let formUpdate= document.getElementById('formUpdate')
            formUpdate.addEventListener('submit',function (e){
                e.preventDefault();
                const xhrService = new XMLHttpRequest()
                let data = new FormData(formUpdate)
                xhrService.onload=()=> {
                    if (xhrService.readyState == 4 && xhrService.status == 200) {
                        if (xhrService.responseText.startsWith("Erreur",0)) {
                            alert(xhrService.responseText)
                        }else {
                            alert(xhrService.responseText)
                            supModal()
                            getProducts()
                        }
                    }else {
                        alert("connexion avec le serveur échouée")
                    }
                }
                xhrService.open("POST","/postUpdateProduct?tokenId="+tokenId)
                xhrService.send(data)
            })
            document.getElementById('btnCancel').addEventListener('click', supModal)
            document.querySelector('.shadowBox').addEventListener('click',supModal)
            history.replaceState({page: 2}, "EspaceAdmin-Produits", "EspaceAdmin-Produits")
        }else {
            alert("connexion avec le serveur échouée")
        }
    }
}
function getModalDelete(productId){
    supModal()
    let xhrRouter = new XMLHttpRequest()
    xhrRouter.open("GET","/getDeleteModale/"+productId+"?tokenId="+tokenId)
    xhrRouter.send()
    xhrRouter.onload=()=> {
        if (xhrRouter.readyState == 4 && xhrRouter.status == 200) {
            divModal.innerHTML += xhrRouter.response
            divTempModal = document.getElementById('tempModal')
            document.querySelector('.shadowBox').addEventListener('click',supModal)
            document.getElementById('btnDeleteProduct').addEventListener('click',postDeleteProduct)
            document.getElementById('btnCancel').addEventListener('click', supModal)
            history.replaceState({page: 2}, "EspaceAdmin-Produits", "EspaceAdmin-Produits")
        }else {
            alert("connexion avec le serveur échouée")
        }
    }
}
function postDeleteProduct(){
    let productId = document.getElementById('idDeleteProduct').textContent
    let xhrService = new XMLHttpRequest()
    xhrService.open("POST","/postDeleteProduct/"+productId+"?tokenId="+tokenId)
    xhrService.send()
    xhrService.onload=()=> {
        if (xhrService.readyState == 4 && xhrService.status == 200) {
            if (xhrService.responseText.startsWith("Erreur",0)) {
                alert(xhrService.responseText)
            }else {
                alert(xhrService.responseText)
                supModal()
                getProducts()
            }
        }else {
            alert("connexion avec le serveur échouée")
        }
    }
}

function getModalAddSales(productId){
    supModal()
    let xhrRouter = new XMLHttpRequest()
    xhrRouter.open("GET","/getAddSalesModale/"+productId+"?tokenId="+tokenId)
    xhrRouter.send()
    xhrRouter.onload=()=> {
        if (xhrRouter.readyState == 4 && xhrRouter.status == 200) {
            divModal.innerHTML += xhrRouter.response
            divTempModal = document.getElementById('tempModal')
            let formAddSales= document.getElementById('formAddSales')
            formAddSales.addEventListener('submit',function (e){
                e.preventDefault();
                const xhrService = new XMLHttpRequest()
                let data = new FormData(formAddSales)
                xhrService.onload=()=> {
                    if (xhrService.readyState == 4 && xhrService.status == 200) {
                        if (xhrService.responseText.startsWith("Erreur",0)) {
                            alert(xhrService.responseText)
                        }else {
                            alert(xhrService.responseText)
                            supModal()
                            getPromo()
                        }
                    }else {
                        alert("connexion avec le serveur échouée")
                    }
                }
                xhrService.open("POST","/postAddSales?tokenId="+tokenId)
                xhrService.send(data)
            })
            document.getElementById('btnCancel').addEventListener('click', supModal)
            document.querySelector('.shadowBox').addEventListener('click',supModal)
            history.replaceState({page: 3}, "EspaceAdmin-Promotions", "EspaceAdmin-Promotions")
        }else {
            alert("connexion avec le serveur échouée")
        }
    }
}


window.addEventListener('load',windowLoad)
btnDeconnexion.addEventListener('click',deconnexion)
