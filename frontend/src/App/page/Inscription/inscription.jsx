
function Inscription(){

    return(

        <div className="countainer_inscription">

            <h1>Pay My Buddy</h1>

            <form className="countainer_inscription__inscriptionForm">

                <label>
                    <input 
                        type="text" name="username" id="username_inscription" placeholder="Username" required
                    />
                </label>

                <label>
                    <input
                        type="email" name="email" id="email_inscription" placeholder="Email" required
                    />
                </label>

                <label>
                    <input
                        type="password" name="password" id="password_inscription" placeholder="Mot de passe" required
                    />
                </label>

                <button>
                    S'inscrire
                </button>

            </form>

        </div>

    );

}

export default Inscription;