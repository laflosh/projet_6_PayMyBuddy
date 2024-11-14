import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getAuthenticatedUser, getItemInLocalStorage, redirectionTo, setItemInLocalStorage} from "../../lib/common";
import { getDataOfConnectedUser, getConnectionsOfConnectedUser, getSenderTransactionsOfConnectedUser,sendNewTransaction } from "../../lib/request";
import { API_ROUTES, APP_ROUTES} from "../../utils/constant";


function Transfer(){
    const navigate = useNavigate();

    const connectedUser = getAuthenticatedUser();
    let [userData, setUserData] = useState(null);
    let [userConnections, setUserConnections] = useState(null);
    let [userSenderTransactions, setUserSenderTransactions] = useState(null);

    let [newTransaction, setNewTransaction] = useState(null);
    let [showResumeTransaction, setShowResumeTransaction] = useState(false);

    let [connection, setConnection] = useState();
    let [description, setDescription] = useState("");
    let [amount, setAmount] = useState(0);


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
                    onChange={(e) => setConnection(parseInt(e.target.value))}
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

                <button
                    onClick={async (e) => {
                       let newTransaction = await sendNewTransaction(e, userData.id, connection, description, amount);
                       setNewTransaction(newTransaction);
                       setShowResumeTransaction(true);
                    }}
                >
                    Payer
                </button>

            </div>

            {newTransaction && showResumeTransaction && 
                <div 
                    className={`resume-new-transaction ${showResumeTransaction ? "resume-new-transaction-over" : ""}`}
                >
                    <p>Résumé de la transaction :</p>

                    <p>Envoyé par <strong>{newTransaction.sender.username}</strong></p>

                    <p>Reçu par <strong>{newTransaction.receiver.username}</strong></p>

                    <p>D'un montant de <strong>{newTransaction.amount} €</strong></p>

                    <p>Raison : <strong>{newTransaction.description}</strong></p>

                    <button
                        onClick={() => {
                            setShowResumeTransaction(false);
                            setNewTransaction(null);

                            getSenderTransactionsOfConnectedUser(connectedUser.connectedUserInfo.id)
                            .then(() => {
                                const cacheUserSenderTransactions = getItemInLocalStorage("userSendertransactions");
                                setUserSenderTransactions(cacheUserSenderTransactions);
                            });
                            window.location.reload();
                        }}
                    >
                        OK
                    </button>

                </div>
            }

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
                                <th className="resume-amount">{transaction.amount} €</th>

                            </tr>
                            
                        ))}

                    </tbody>

                </table>

            </div>

        </div>

    );

}

export default Transfer;