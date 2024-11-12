//Communs fonctions for front-end app

export function redirectionTo(navigate, path){

    navigate(path);

}

export function setItemInLocalStorage(key, value){

    localStorage.setItem(key, JSON.stringify(value));

}

export function getItemInLocalStorage(key){

    let data = localStorage.getItem(key);

    return JSON.parse(data);

}

export function deleteItemInLocalStorage(key){

    localStorage.removeItem(key);

}

export function clearLocalStorage(){

    localStorage.clear();

}

export function getAuthenticatedUser(){

    const defaultReturnObject = {
        "authenticated" : false,
        "connectedUserInfo" : null
    }

    try {
        
        const connectedUser = getItemInLocalStorage("connectedUser");

        if(!connectedUser){
            return defaultReturnObject;
        }

        return {
            "authenticated" : true,
            "connectedUserInfo" : {connectedUser}
        }

    } catch (error) {
        
        console.error("Something went wrong when getting authenticated user. ", error);
        return defaultReturnObject;

    }

}