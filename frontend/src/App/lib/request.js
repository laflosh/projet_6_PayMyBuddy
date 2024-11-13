//All requests to the Spring Boot backend For the front-end application

import { API_ROUTES, APP_ROUTES } from "../utils/constant";
import { clearLocalStorage, redirectionTo, setItemInLocalStorage} from "../lib/common.js";

export async function logIn(event, navigate, email, password){

    event.preventDefault();
    
    const logInData = {username : email, password : password}

    try{

        let response = await fetch(API_ROUTES.LOG_IN ,{

            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded", 
            },
            body: new URLSearchParams(logInData).toString(),
            credentials: "include"

        })

        if(response.ok){

            const data = await response.json();
            console.log(data);

            setItemInLocalStorage("connectedUser", data);
            redirectionTo(navigate, APP_ROUTES.TRANSFER);

        } else {

            console.error("Invalid email or password.");

        }

    } catch(error) {
        
        console.error("Failed to log in", error);

    }

}

export async function logOut(event, navigate){

    event.preventDefault();

    try{

        let response = await fetch(API_ROUTES.LOG_OUT ,{

            method : "POST",
            headers : {
                "Content-Type" : "application/json"
            },
            credentials : "include",

        });

        if(response.ok){

            clearLocalStorage();

            redirectionTo(navigate, APP_ROUTES.SIGN_IN);

        } else {

            console.error("Something went wrong during  loging out.")

        }

    } catch (error){

        console.error("Can't log out of your session. ", error);

    }

}

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