import React from 'react';
import App from './App';
import './styles/index.scss'
import * as serviceWorker from './serviceWorker';
import 'bootstrap/dist/css/bootstrap.min.css';
import {ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import ReactDOM from 'react-dom';
import {BrowserRouter} from "react-router-dom";


const app = (
    <div >
        <ToastContainer/>
        <BrowserRouter>
            <App/>
        </BrowserRouter>
    </div>
);

ReactDOM.render(
    app,
    document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();