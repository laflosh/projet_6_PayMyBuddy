import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { redirectionTo} from "../../lib/common.js";
import { logIn } from "../../lib/request.js";
import { APP_ROUTES } from "../../utils/constant";

function SignIn(){

    const navigate = useNavigate();
    let [email , setEmail] = useState("");
    let [password, setPassword] = useState("");

    return(

        <div className = "countainer_logIn">

            <h1>Pay My Buddy</h1>

            <form className="countainer_logIn__signInForm">

                <label>
                    <input 
                        type="text" name="email" id="email_logIn" placeholder="Email" required
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </label>

                <label>
                    <input 
                        type="password" name="password" id="password_logIn" placeholder="Mot de passe" required
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </label>

                <button 
                    onClick={(e) => logIn(e, navigate, email, password)}
                >
                    Se connecter
                </button>

            </form>

            <button class = "countainer_logIn__btn_ToInscription"
                onClick={() => redirectionTo(navigate, APP_ROUTES.SIGN_UP)}
                >
                M'inscrire
            </button>

        </div>

    );

}

export default SignIn;