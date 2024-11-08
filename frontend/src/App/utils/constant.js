const API_URL = "http//localhost:8081";

export const API_ROUTES = {
    LOG_IN:`${API_URL}/login`,
    LOG_OUT:`${API_URL}/logout`,
    USERS:`${API_URL}/api/users`,
    USER_CONNECTIONS:`${API_URL}/api/users/:id/connections`,
    TRANSACTIONS:`${API_URL}/api/transactions`,
    SENDER_TRANSACTIONS:`${API_URL}/api/transactions/sender/:id`,
    RECEIVER_TRANSACTIONS:`${API_URL}/api/transactions/receiver/:id`,
};

export const APP_ROUTES = {
    SIGN_IN : "/",
    SIGN_UP : "/inscription",
    CONNECTION : "/connection",
    TRANSFER: "/transfer",
    PROFIL: "/profil",
};