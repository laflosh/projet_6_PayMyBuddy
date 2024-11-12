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

            redirectionTo(navigate, APP_ROUTES.SIGN_IN);

        }

    });

}

export default Connection