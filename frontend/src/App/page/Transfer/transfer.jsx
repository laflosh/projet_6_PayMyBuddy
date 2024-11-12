import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getAuthenticatedUser, redirectionTo } from "../../lib/common";
import { APP_ROUTES } from "../../utils/constant";


function Transfer(){

    const connectedUser = getAuthenticatedUser();
    const navigate = useNavigate();

    console.log(connectedUser);

    useEffect(() => {

        if(connectedUser.authenticated === false){

            redirectionTo(navigate, APP_ROUTES.SIGN_IN);

        }

    });

    return(

        <div className="countainer_transfer">



        </div>

    );

}

export default Transfer;