import { API_ROUTES } from "../../utils/constant";
import { setItemInLocalStorage, findConnectionByEmail } from "../common";


export async function getConnectionsOfConnectedUser(id){

    let url = API_ROUTES.USER_CONNECTIONS(id);

    try{

        let response = await fetch(url ,{

            method : "GET",
            credentials : "include",

        })

        if(response.ok){

            let data = await response.json();
            setItemInLocalStorage("userConnections", data);

        } else {

            console.error("Can't find user's connections.");

        }

    }catch(error){

        console.error("Can't fetching in the database. ", error);

    }

}




export async function addNewConnectionForConnectedUser(event, email, userId){

    event.preventDefault();

    let url = API_ROUTES.USER_CONNECTIONS(userId);
    try {

        let response = await fetch(url, {

            method : "POST",
            headers : {

                "Content-Type" : "text/plain",

            },
            body : email,
            credentials : "include",

        })

        if(response.status === 201){

            let data = await response.json();

            setItemInLocalStorage("userConnections", data);

        } else {

            console.error("Can't add the connection. Status ", response.status);

        }

    } catch (error) {
        
        console.error("Failure to add connection to an user", error)

    }

};

export async function deleteConnectionOfAConnectedUser(event, userId, email){

    event.preventDefault();

    let connection = findConnectionByEmail(email);

    let url = API_ROUTES.USER_CONNECTIONS(userId);

    try{

        let response = await fetch(`${url}/${connection.id}`, {

            method : "DELETE",
            headers : {
                "Content-Type" : "application/json",
            },
            credentials : "include",

        })

        if(response.status === 204){

            getConnectionsOfConnectedUser(userId);

        } else {

            console.error("Can't delete the connection. Status ", response.status);

        }

    } catch (error){

        console.error("Failed to delete the connection of the connected user", error);

    }

};