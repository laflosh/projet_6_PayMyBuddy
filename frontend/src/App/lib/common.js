//Communs fonctions for front-end app

export function redirectionTo(navigate, path){

    navigate(path);

}

export function setItemInLocalStorage(key, value){

    localStorage.setItem(key, value);

}

export function getItemInLocalStorage(key){

    return localStorage.getItem(key);

}

export function deleteItemInLocalStorage(key){

    localStorage.removeItem(key);

}

export function clearLocalStorage(){

    localStorage.clear();

}