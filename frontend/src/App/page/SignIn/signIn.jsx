
function SignIn(){

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

            <button class = "countainerSignIn__btnToInscription">
                M'inscrire
            </button>

        </div>

    );

}

export default SignIn;