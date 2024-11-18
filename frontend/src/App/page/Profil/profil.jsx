/* eslint-disable no-template-curly-in-string */
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getAuthenticatedUser, getItemInLocalStorage, redirectionTo } from "../../lib/common";
import { updateUserConnectedInfo, deleteAnUser } from "../../lib/request";
import { APP_ROUTES } from "../../utils/constant";

function Profil(){

    const connectedUser = getAuthenticatedUser();
    const navigate = useNavigate();

    let userData = getItemInLocalStorage("userData");

    let [username, setUsername] = useState();
    let [email, setEmail] = useState();
    let [password, setPassword] = useState();

    let [message, setMessage] = useState("");
    let [showMessageUpdate, setShowMessageUpdate] = useState(false);
    let [showMessagDelete, setShowMessageDelete] = useState(false);

    useEffect(() => {

        if(connectedUser.authenticated === false){

            console.error("You need to be connected at an account for seeing this page.");
            redirectionTo(navigate, APP_ROUTES.SIGN_IN);

        };

    });

    function updatedMessage(message){

        setTimeout(() => {

            setShowMessageUpdate(false);
            setMessage("");

        }, 5000);

        return(

            <div className="update_message">

                <p>{message}</p>

            </div>

        );

    }

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
                            .then(() => {
                                setMessage("Le profil a bien été mise à jour !");
                                setShowMessageUpdate(true)});
                            }
                        }
                    >
                        Modifier
                    </button>

                    <button className="btn_delete_profil"
                        onClick={(e) => deleteAnUser(e, navigate, userData.id)}
                    >
                        Supprimer profil
                    </button>

                </div>

            </form>

            {showMessageUpdate && updatedMessage(message)}

        </div>

    );

};

export default Profil;