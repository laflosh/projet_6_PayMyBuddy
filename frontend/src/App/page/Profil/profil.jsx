/* eslint-disable no-template-curly-in-string */
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getAuthenticatedUser, getItemInLocalStorage, redirectionTo } from "../../lib/common";
import { updateUserConnectedInfo } from "../../lib/request";
import { APP_ROUTES } from "../../utils/constant";

function Profil(){

    const connectedUser = getAuthenticatedUser();
    const navigate = useNavigate();

    let userData = getItemInLocalStorage("userData");

    let [username, setUsername] = useState();
    let [email, setEmail] = useState();
    let [password, setPassword] = useState();

    useEffect(() => {

        if(connectedUser.authenticated === false){

            console.error("You need to be connected at an account for seeing this page.");
            redirectionTo(navigate, APP_ROUTES.SIGN_IN);

        };

    });

    return(

        <div className="countainer_profil">

            <form className="countainer_profil__form">

                <div className="countainer_profil__form-content">

                    <div>

                        <label for="username">Username</label>
                        <input 
                            type="text" name="username" id="username_profil" placeholder={`@${userData.username || ""}`}
                            onChange={(e) => setUsername(e.target.value)}
                        />

                    </div>

                    <div>

                        <label for="email">Mail</label>
                        <input 
                            type="email" name="email" id="email_profil" placeholder={`${userData.email || ""}`}
                            onChange={(e) => setEmail(e.target.value)}
                        />

                    </div>

                    <div>

                        <label for="password">Mot de passe</label>
                        <input 
                            type="password" name="password" id="password_profil" placeholder="nouveau mot de passe"
                            onChange={(e) => setPassword(e.target.value)}
                        />

                    </div>

                </div>

                <div className="countainer_profil__form-btn">

                    <button className="btn_update_profil"
                        onClick={(e) => {
                            updateUserConnectedInfo(e, userData.id, username, email, password)
                        }}
                    >
                        Modifier
                    </button>

                </div>

            </form>

        </div>

    );

};

export default Profil;