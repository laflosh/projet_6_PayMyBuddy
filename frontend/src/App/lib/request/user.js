import { API_ROUTES, APP_ROUTES } from "../../utils/constant";
import { setItemInLocalStorage, redirectionTo } from "../common";

export async function getDataOfConnectedUser(id){

    try{

        let response = await fetch(`${API_ROUTES.USERS}/${id}`,{

            method : "GET",
            credentials: "include"

        } )

        if(response.ok){

            let data = await response.json();
            setItemInLocalStorage("userData", data);

        } else {

            console.error("Can't find connected user in database.")

        }

    } catch(error) {

        console.error("Can't fetching in the database. ", error);

    }

}

export async function createNewUser(event, username, email, password, navigate){

    event.preventDefault();

    let dataNewUser = {
        "username" : username,
        "email" : email,
        "password" : password
    };

    try {

        let response =  await fetch(API_ROUTES.USERS, {

            method : "POST",
            headers : {
                "Content-Type" : "application/json"
            },
            body : JSON.stringify(dataNewUser),
            credentials : "include",

        });

        if(response.status === 201){

            let createdUser = await response.json();
            console.log(createdUser);

            redirectionTo(navigate, APP_ROUTES.SIGN_IN);

        } else {

            console.error("Wrong argument for creating user. Status : " + response.status);

        }

    } catch (error) {

        console.error("Failed to create the user in database.", error);

    }

}

export async function updateUserConnectedInfo(event, userId, newUsername, newEmail, newPassword){

    event.preventDefault();

    let updateData = {
        "id" : userId,
        "username" : newUsername,
        "email" : newEmail,
        "password" : newPassword,
    };

    try{

        let response = await fetch(API_ROUTES.USERS,{

            method : "PUT",
            headers : {
                "Content-Type" : "application/json"
            },
            body : JSON.stringify(updateData),
            credentials : "include",

        });

        if(response.status === 201){

            let data = await response.json();
            setItemInLocalStorage("userData", data);

        } else {

            console.error("Can't updated the user. Status", response.status);

        }

    } catch(error){

        console.error("Failed to update the connected User. ", error);

    };

};

export async function deleteAnUser(event, navigate, userId){

    event.preventDefault();

    let url = `${API_ROUTES.USERS}/${userId}`;

    try {

        let response = await fetch(url, {

            method : "DELETE",
            headers : {
                "Content-Type" : "application/json",
            },
            credentials : "include",

        });

        if(response.status === 204) {

            redirectionTo(navigate, APP_ROUTES.SIGN_IN);

        } else {

            console.error("Can't delete an existing user. Status ", response.status);

        }

    } catch(error) {

        console.error("Failure delete the user in database. ", error);

    }

}