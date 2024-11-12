import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { createNewUser } from "../../lib/request";

function Inscription(){

    let navigate = useNavigate();
    let [username, setUsername] = useState();
    let [email, setEmail] = useState();
    let [password, setPassword] = useState();

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