/* eslint-disable no-template-curly-in-string */
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getAuthenticatedUser, getItemInLocalStorage, redirectionTo } from "../../lib/common";
import { APP_ROUTES } from "../../utils/constant";

function Profil(){

    const connectedUser = getAuthenticatedUser();
    const navigate = useNavigate();

    let userData = getItemInLocalStorage("userData");

    console.log(userData);

    useEffect(() => {

        if(connectedUser.authenticated === false){

            console.error("You need to be connected at an account for seeing this page.");
            redirectionTo(navigate, APP_ROUTES.SIGN_IN);

        }

    });

    async function updateUserConnectedInfo(){

        

    }

    return(

        <div className="countainer_profil">

            <form className="countainer_profil__form">

                <div className="countainer_profil__form-content">

                    <div>

                        <label for="username">Username</label>
                        <input type="text" name="username" id="username_profil" placeholder={`@${userData.username || ""}`}/>

                    </div>

                    <div>

                        <label for="email">Mail</label>
                        <input type="email" name="email" id="email_profil" placeholder={`@${userData.email || ""}`}/>

                    </div>

                    <div>

                        <label for="password">Mot de passe</label>
                        <input type="password" name="password" id="password_profil" placeholder="nouveau mot de passe"/>

                    </div>

                </div>

                <div className="countainer_profil__form-btn">

                    <button className="btn_update_profil">
                        Modifier
                    </button>

                </div>

            </form>

        </div>

    );

};

export default Profil;