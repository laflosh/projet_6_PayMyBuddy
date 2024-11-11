//All requests to the Spring Boot backend For the front-end application

import { API_ROUTES, APP_ROUTES } from "../utils/constant";
import { redirectionTo} from "../lib/common.js";

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
            redirectionTo(navigate, APP_ROUTES.TRANSFER);

        } else {

            console.error("Invalid email or password.");

        }

    } catch(error) {
        
        console.error("Failed to log in", error);

    }

}