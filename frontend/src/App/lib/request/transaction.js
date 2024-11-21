import { API_ROUTES } from "../../utils/constant";
import { setItemInLocalStorage } from "../common";

export async function getSenderTransactionsOfConnectedUser(id){

    try{

        let response = await fetch(`${API_ROUTES.SENDER_TRANSACTIONS}${id}`, {

            method : "GET",
            credentials : "include",

        })

        if(response.ok){

            let data = await response.json();
            setItemInLocalStorage("userSendertransactions", data);

        } else {

            console.error("Can't find user's sender transactions.")

        } 

    } catch(error){

        console.error("Can't find user's connections.");

    }

}



export async function sendNewTransaction(event, senderId, receiverId, description, amount){
    event.preventDefault();

    let dataTransaction = {
        "senderId" : senderId,
        "receiverId" : receiverId,
        "description" : description,
        "amount" : amount,
    };

    try{

        let response = await fetch(API_ROUTES.TRANSACTIONS, {

            method : "POST",
            headers : {
                "Content-Type" : "application/json"
            },
            body : JSON.stringify(dataTransaction),
            credentials : "include",
        });

        if(response.status === 201){

            let data = await response.json();
            return data;

        } else{

            console.error("Can't sending a new transaction. ", response.status);

        }

    } catch(error) {

        console.error("Failed to create a new transaction.");

    }

}