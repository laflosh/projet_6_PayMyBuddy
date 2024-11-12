import { useLocation } from "react-router-dom";
import { useState, useEffect } from "react";
import { APP_ROUTES } from "../../utils/constant";

function Header(){

    const location = useLocation();
    let [isShow, setIsShow] = useState(true);

    useEffect(() => {

        if(location.pathname === "/" || location.pathname === "/inscription"){

            setIsShow(false);

        } else {

            setIsShow(true);

        }

    }, [location]);

    return(

        isShow ? (    
        
        <div className="countainer_header">
            
            <p>Pay My Buddy</p>

            <nav>

                <a href={APP_ROUTES.TRANSFER}>
                    Transférer
                </a>

                <a href={APP_ROUTES.PROFIL}>
                    Profil
                </a>

                <a href={APP_ROUTES.CONNECTION}>
                    Ajouter relation
                </a>

                <button
                    //onClick={() => }
                >
                    Se déconnecter
                </button>

            </nav>

        </div>

        ) : null 
        
    );

}

export default Header;