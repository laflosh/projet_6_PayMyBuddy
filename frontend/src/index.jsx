import React from 'react';
import ReactDOM from 'react-dom/client';
import './App/style/main.scss';
import App from './App/App';
import reportWebVitals from './Test/reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(

  <React.StrictMode>
    <App />
  </React.StrictMode>
  
);

reportWebVitals();
