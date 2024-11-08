import { useNavigate } from "react-router-dom";
import { APP_ROUTES } from "../../utils/constant";

function SignIn(){

    const navigate = useNavigate();

    function redirectionTo(path){

        navigate(`${path}`);

    }

    return(

        <div className = "countainerSignIn">

            <h1>Pay My Buddy</h1>

            <form className="countainerSignIn__signInForm">

                <label>
                    <input for="text" name="email" id="email" placeholder="Email" required/>
                </label>

                <label>
                    <input for="text" name="password" id="password" placeholder="Mot de passe" required/>
                </label>

                <button>
                    Se connecter
                </button>

            </form>

            <button class = "countainerSignIn__btnToInscription"
                onClick={redirectionTo(APP_ROUTES.SIGN_UP)}
                >
                M'inscrire
            </button>

        </div>

    );

}

export default SignIn;