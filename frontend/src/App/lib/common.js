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
        
        const connectedUserInfo = getItemInLocalStorage("connectedUser");

        if(!connectedUserInfo){
            return defaultReturnObject;
        }

        return {
            "authenticated" : true,
            connectedUserInfo
        }

    } catch (error) {
        
        console.error("Something went wrong when getting authenticated user. ", error);
        return defaultReturnObject;

    }

}

export function findConnectionByEmail(email){

    let connections = getItemInLocalStorage("userConnections");
    let connection = connections.find((connection) => {
        return connection.email === email
    });

    return connection;

};