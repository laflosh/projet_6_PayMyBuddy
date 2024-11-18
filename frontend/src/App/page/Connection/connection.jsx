import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getAuthenticatedUser, redirectionTo } from "../../lib/common";
import { APP_ROUTES } from "../../utils/constant";

function Connection(){

    const connectedUser = getAuthenticatedUser();
    const navigate = useNavigate();

    console.log(connectedUser);

    useEffect(() => {

        if(connectedUser.authenticated === false){

            console.error("You need to be connected at an account for seeing this page.");
            redirectionTo(navigate, APP_ROUTES.SIGN_IN);

        }

    });

    return(

        <div className="countainer_connection">

            <div className="search_connection">

                <label for="email">Chercher une relation</label>
                <input type="email" name="email" id="email_connection" placeholder="Saisir une adresse mail"/>

            </div>

            <div className="btn_action">

                <button className="btn_action__add_connection">
                    Ajouter
                </button>

            </div>

        </div>

    );

}

export default Connection