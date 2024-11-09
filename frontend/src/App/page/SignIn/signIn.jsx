import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { redirectionTo} from "../../lib/common.js";
import { APP_ROUTES, API_ROUTES } from "../../utils/constant";

function SignIn(){

    const navigate = useNavigate();
    let [email , setEmail] = useState("");
    let [password, setPassword] = useState("");

    async function logIn(event, email, password){
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

    return(

        <div className = "countainerSignIn">

            <h1>Pay My Buddy</h1>

            <form className="countainerSignIn__signInForm">

                <label>
                    <input 
                        type="text" name="email" id="email" placeholder="Email" required
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </label>

                <label>
                    <input 
                        type="text" name="password" id="password" placeholder="Mot de passe" required
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </label>

                <button 
                    onClick={(e) => logIn(e, email, password)}
                >
                    Se connecter
                </button>

            </form>

            <button class = "countainerSignIn__btnToInscription"
                onClick={() => redirectionTo(navigate, APP_ROUTES.SIGN_UP)}
                >
                M'inscrire
            </button>

        </div>

    );

}

export default SignIn;