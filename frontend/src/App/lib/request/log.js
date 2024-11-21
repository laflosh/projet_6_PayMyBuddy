import { API_ROUTES, APP_ROUTES } from "../../utils/constant";
import { setItemInLocalStorage, redirectionTo, clearLocalStorage } from "../common";

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