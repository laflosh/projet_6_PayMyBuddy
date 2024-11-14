import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getAuthenticatedUser, getItemInLocalStorage, redirectionTo, setItemInLocalStorage} from "../../lib/common";
import { getDataOfConnectedUser, getConnectionsOfConnectedUser, getSenderTransactionsOfConnectedUser } from "../../lib/request";
import { API_ROUTES, APP_ROUTES} from "../../utils/constant";


function Transfer(){
    const navigate = useNavigate();

    const connectedUser = getAuthenticatedUser();
    let [userData, setUserData] = useState(null);
    let [userConnections, setUserConnections] = useState(null);
    let [userSenderTransactions, setUserSenderTransactions] = useState(null);

    let [connection, setConnection] = useState();
    let [description, setDescription] = useState("");
    let [amount, setAmount] = useState(0);

    console.log(connectedUser);
    console.log(userData);
    console.log(userConnections);
    console.log(userSenderTransactions);

    function handleChangeInputNumber(event){

        let value = event.target.value;
        setAmount(value);

    }

    function fetchData(connectedUser){


        if(userData === null){

            getDataOfConnectedUser(connectedUser.connectedUserInfo.id)
            .then(() => {
                const cacheUserData = getItemInLocalStorage("userData");
                setUserData(cacheUserData);
            });
    
        }

        if(userConnections === null){

            getConnectionsOfConnectedUser(connectedUser.connectedUserInfo.id)
            .then(() => {
                const cacheUserConnections = getItemInLocalStorage("userConnections");
                setUserConnections(cacheUserConnections);
            });

        }

        if(userSenderTransactions === null){

            getSenderTransactionsOfConnectedUser(connectedUser.connectedUserInfo.id)
            .then(() => {
                const cacheUserSenderTransactions = getItemInLocalStorage("userSendertransactions");
                setUserSenderTransactions(cacheUserSenderTransactions);
            });

        }

    }

    useEffect(() => {

        if(connectedUser.authenticated === false){

            console.error("You need to be connected at an account for seeing this page.");
            redirectionTo(navigate, APP_ROUTES.SIGN_IN);

        }

    });

    useEffect(() => {

        fetchData(connectedUser);

    })

    return(

        <div className="countainer_transfer">

            <div className="transaction">

                <select className="transaction__connection"
                    onChange={(e) => setConnection(e.target.value)}
                >

                    <option value="">Sélectionner une relation</option>
                    {userConnections && userConnections.map((connection, index) => (

                        <option key={index} value={connection.id}>{connection.username}</option>

                    ))}

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

                        {userSenderTransactions && userSenderTransactions.map((transaction, index) => (
                            <tr key={index}>

                                <th scope="row">{transaction.receiver.username}</th>
                                <th>{transaction.description}</th>
                                <th className="resume-amount">{transaction.amont} €</th>

                            </tr>
                            
                        ))}

                    </tbody>

                </table>

            </div>

        </div>

    );

}

export default Transfer;