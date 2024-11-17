import { APP_ROUTES} from "./utils/constant.js";
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Header from "./component/Header/header.jsx";
import Footer from "./component/Footer/footer.jsx";
import SignIn from "./page/SignIn/signIn.jsx";
import Inscription from "./page/Inscription/inscription.jsx";
import Transfer from "./page/Transfer/transfer.jsx";
import Profil from "./page/Profil/profil.jsx";
import Connection from "./page/Connection/connection.jsx";
import Error from "./page/Error/error.jsx";

function App() {

  return(

    <BrowserRouter>

      <div>

        <Header/>

          <Routes>

            <Route index path={APP_ROUTES.SIGN_INSIGN_IN} element={<SignIn/>}/>
            <Route path={APP_ROUTES.SIGN_UP} element={<Inscription/>}/>
            <Route path={APP_ROUTES.TRANSFER} element={<Transfer/>}/>
            <Route path={APP_ROUTES.PROFIL} element={<Profil/>}/>
            <Route path={APP_ROUTES.CONNECTION} element={<Connection/>}/>
            <Route path="*" element={<Error/>}/>

          </Routes>

        <Footer/>

      </div>
    
    </BrowserRouter>

  );

}

export default App;
