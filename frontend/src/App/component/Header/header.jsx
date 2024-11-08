import { useLocation } from "react-router-dom";
import { useState, useEffect } from "react";

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

    console.log(location.pathname);

    return(

        isShow ? (    
        
        <div id="header">
            La
        </div>

        ) : null 
        
    );

}

export default Header;