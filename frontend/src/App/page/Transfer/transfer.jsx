import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getAuthenticatedUser, redirectionTo } from "../../lib/common";
import { APP_ROUTES } from "../../utils/constant";


function Transfer(){

    const connectedUser = getAuthenticatedUser();
    const navigate = useNavigate();
    let [connection, setConnection] = useState();
    let [description, setDescription] = useState("");
    let [amount, setAmount] = useState(0);

    console.log(connectedUser);

    useEffect(() => {

        if(connectedUser.authenticated === false){

            console.error("You need to be connected at an account for seeing this page.");
            redirectionTo(navigate, APP_ROUTES.SIGN_IN);

        }

    });

    function handleChangeInputNumber(event){

        let value = event.target.value;
        setAmount(value);

    }

    return(

        <div className="countainer_transfer">

            <div className="transaction">

                <select className="transaction__connection"
                    onChange={(e) => setConnection(e.target.value)}
                >

                    <option value="">Sélectionner une relation</option>

                </select>

                <input 
                    type="text" name="description" id="description_transaction" className="transaction__description" placeholder="Description"
                    onChange={(e) => setDescription(e.target.value)}
                />

                <span className="input_number_symbole">
                    <input
                        type="number" name="amount" id="amount_transaction" className="transaction__amount" min="0" max="999" 
                        value={amount}
                        onChange={(e) => handleChangeInputNumber(e)} 
                    />
                </span>

                <button>
                    Payer
                </button>

            </div>

            <div className="resume">

                <table>

                    <caption>Mes transactions</caption>

                    <thead>

                        <tr>
                            <th scope="col">Relations</th>
                            <th scope="col">Description</th>
                            <th scope="col">Montant</th>
                        </tr>

                    </thead>

                    <tbody>

                        <tr>
                            <th scope="row">texte</th>
                            <th>texte</th>
                            <th>montant</th>
                        </tr>

                    </tbody>

                </table>

            </div>

        </div>

    );

}

export default Transfer;