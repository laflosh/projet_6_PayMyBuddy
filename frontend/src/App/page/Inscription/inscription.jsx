import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { API_ROUTES, APP_ROUTES } from "../../utils/constant";
import { redirectionTo } from "../../lib/common";

function Inscription(){

    let navigate = useNavigate();
    let [username, setUsername] = useState();
    let [email, setEmail] = useState();
    let [password, setPassword] = useState();

    async function createNewUser(event, username, email, password, navigate){

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

                let createdUser = response.json();
                console.log(createdUser);

                redirectionTo(navigate, APP_ROUTES.SIGN_IN);

            } else {

                console.error("Wrong argument for creating user. Status : " + response.status);

            }

        } catch (error) {

            console.error("Failed to create the user in database.", error);

        }

    }

    return(

        <div className="countainer_inscription">

            <h1>Pay My Buddy</h1>

            <form className="countainer_inscription__inscriptionForm">

                <label>
                    <input 
                        type="text" name="username" id="username_inscription" placeholder="Username" required
                        onChange={(e) => setUsername(e.target.value)}
                    />
                </label>

                <label>
                    <input
                        type="email" name="email" id="email_inscription" placeholder="Email" required
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </label>

                <label>
                    <input
                        type="password" name="password" id="password_inscription" placeholder="Mot de passe" required
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </label>

                <button
                    onClick={(e) => createNewUser(e, username, email, password, navigate)}
                >
                    S'inscrire
                </button>

            </form>

        </div>

    );

}

export default Inscription;