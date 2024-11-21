import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getAuthenticatedUser, redirectionTo, getItemInLocalStorage } from "../../lib/common";
import { addNewConnectionForConnectedUser, deleteConnectionOfAConnectedUser } from "../../lib/request.js";
import { APP_ROUTES } from "../../utils/constant";

function Connection(){

    const connectedUser = getAuthenticatedUser();
    let userData = getItemInLocalStorage("userData");
    const navigate = useNavigate();

    let [emailConnection, setEmailConnection] = useState("");
    let [showAddMessage, setShowAddMessage] = useState(false);
    let [showDeleteMessage, setShowDeleteMessage] = useState(false);

    console.log(connectedUser);

    useEffect(() => {

        if(connectedUser.authenticated === false){

            console.error("You need to be connected at an account for seeing this page.");
            redirectionTo(navigate, APP_ROUTES.SIGN_IN);

        }

    });

    function messageAddConnection(){
        
       setTimeout(() => {

        setShowAddMessage(false);

       }, 3000)

        return(

            <div className="add_connection_message">

                <p>La relation a bien été ajouter à votre compte !</p>

            </div>

        )

    };

    return(

        <div className="countainer_connection">

            <div className="content">

                <div className="search_connection">

                    <label for="email">Chercher une relation</label>
                    <input 
                        type="email" name="email" id="email_connection" placeholder="Saisir une adresse mail" required
                        onChange={(e) => setEmailConnection(e.target.value)}
                    />

                </div>

                <div className="btn_action">

                    <button className="btn_action__add_connection"
                        onClick={(e) => {
                            addNewConnectionForConnectedUser(e, emailConnection, userData.id)
                            .then(() => setShowAddMessage(true));
                        }}
                    >
                        Ajouter
                    </button>

                    <button className="btn_action__delete_connection"
                        onClick={(e) => {
                            deleteConnectionOfAConnectedUser(e, userData.id, emailConnection);
                        }}
                    >
                        Supprimer
                    </button>

                </div>

            </div>

            {showAddMessage && messageAddConnection()}

        </div>

    );

};

export default Connection;