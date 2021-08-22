import React from 'react';
import {createBrowserHistory} from 'history'
import {Provider} from "react-redux";
import thunkMiddleware from "redux-thunk";
import {applyMiddleware, combineReducers, compose, createStore} from "redux";
import {routerMiddleware, routerReducer} from "react-router-redux";
import auth from './redux/reducers/authReducer'
import apiMiddleware from './redux/ApiMiddleware'
import {Route, Switch} from "react-router-dom";
import PrivateRoute from "./utils/PrivateRoute";
import PublicRoute from "./utils/PublicRoute";
import NotFound from "./pages/NotFound";
import Home from "./component/home page(Jamshid)/Home"
import Cabinet from "./pages/Cabinet";
import Agent from "./pages/Agent";
import ClientPage from "./pages/Client";
import AdminAgents from "./pages/admin/AdminAgents";
import AdminMain from "./pages/admin/maın/AdminMain";
import Services from "./pages/admin/Services";
import MainService from "./pages/admin/MainService";
import AdditionalService from "./pages/admin/AdditionalService";
import Pricing from "./pages/admin/Pricing";
import ServicePrice from "./pages/admin/ServicePrice";
import AdditionalServicePrice from "./pages/admin/AdditionalServicePrice";
import CompanyWorkTime from "./pages/admin/CompanyWorkTime";
import AdminOrders from "./pages/admin/AdminOrders";
import ZipCodes from "./pages/admin/ZipCodes";
import ZipCodeCurrent from "./pages/admin/ZipCodeCurrent";
import AdminFeedback from "./pages/admin/AdminFeedback";
import Discount from "./pages/admin/Discount";
import Customer from "./pages/admin/Customer";
import Blog from "./pages/admin/Blog";
import Settings from "./pages/admin/Settings";
import ClientMain from "./pages/client/ClientMain";
import ClientOrder from "./pages/client/ClientOrder";
import ClientSharing from "./pages/client/ClientSharing";
import ClientSttengs from "./pages/client/ClientSttengs";
import AgentMain from "./pages/agent/AgentMain";
import AgentOrder from "./pages/agent/AgentOrder";
import AgentSharing from "./pages/agent/AgentSharing";
import AgentSettings from "./pages/agent/AgentSettings";
import AgentWorkTime from "./pages/agent/AgentWorkTime";
import 'bootstrap/dist/css/bootstrap.min.css'
import Schedule from "./component/home page(Jamshid)/schedule/Schedule";
import OtherCustomersSay from "./component/home page(Jamshid)/otherCulstomersSay/OtherCustomersSay";
import admin from './redux/reducers/adminMainReducer'
import zipCode from './redux/reducers/zipCodesReducer'
import state from './redux/reducers/stateReducer'
import county from './redux/reducers/countyReducer'
import zipCodeCurrent from './redux/reducers/zipCodeCurrentReducer'
import feedbacks from './redux/reducers/feedbackReducer'


const history = createBrowserHistory();

const routeMiddleware = routerMiddleware(history);

const middleWares = [
    thunkMiddleware,
    apiMiddleware,
    routeMiddleware
].filter(Boolean);

export const store = createStore(
    combineReducers({
        zipCode,
        admin,
        router:routerReducer,
        auth,
        state,
        county,
        zipCodeCurrent,
        feedbacks
    }),
    compose(
        applyMiddleware(
            ...middleWares
        )
    )
);

const App = () => {
    return (
        <Provider store={store}>
            <Switch>
                <PublicRoute exact path="/" component={Home}/>
                <PublicRoute exact path="/othersSay" component={OtherCustomersSay}/>
                <PublicRoute exact path="/schedule" component={Schedule}/>
                <PrivateRoute exact path="/cabinet" component={Cabinet}/>
                {/*<PrivateRoute exact path="/admin" component={Admin}/>*/}
                {/*<PrivateRoute exact path="/cabinet" component={Cabinet}/>*/}
                <PrivateRoute exact path="/admin/agents" component={AdminAgents}/>
                <PrivateRoute exact path="/admin/adminMain" component={AdminMain}/>
                <PrivateRoute exact path="/admin/service" component={Services}/>
                <PrivateRoute exact path="/admin/servicePrice" component={ServicePrice}/>
                <PrivateRoute exact path="/admin/mainService" component={MainService}/>
                <PrivateRoute exact path="/admin/additionalService" component={AdditionalService}/>
                <PrivateRoute exact path="/admin/additionalServicePrice" component={AdditionalServicePrice}/>
                <PrivateRoute exact path="/admin/pricing" component={Pricing}/>
                <PrivateRoute exact path="/admin/mainServiceWorkTime" component={CompanyWorkTime}/>
                <PrivateRoute exact path="/admin/orders" component={AdminOrders}/>
                <PrivateRoute exact path="/admin/zipCodes" component={ZipCodes}/>


                {/*<PrivateRoute exact path="/admin/states" component={States}/>*/}
                {/*<PrivateRoute exact path="/admin/county" component={County}/>*/}
                <PrivateRoute exact path="/admin/zip/current" component={ZipCodeCurrent}/>


                <PrivateRoute exact path="/admin/feedback" component={AdminFeedback}/>
                <PrivateRoute exact path="/admin/discount" component={Discount}/>
                <PrivateRoute exact path="/admin/customer" component={Customer}/>
                <PrivateRoute exact path="/admin/blog" component={Blog}/>
                <PrivateRoute exact path="/admin/settings" component={Settings}/>
                <PrivateRoute exact path="/agent" component={Agent}/>
                <PrivateRoute exact path="/Client" component={ClientPage}/>
                <PrivateRoute exact path="/client/clientMain" component={ClientMain}/>
                <PrivateRoute exact path="/client/clientOrder" component={ClientOrder}/>
                <PrivateRoute exact path="/client/clientSharing" component={ClientSharing}/>
                <PrivateRoute exact path="/client/clientSettings" component={ClientSttengs}/>
                <PrivateRoute exact path="/agent/agentMain" component={AgentMain}/>
                <PrivateRoute exact path="/agent/agentOrder" component={AgentOrder}/>
                <PrivateRoute exact path="/agent/agentWorkTime" component={AgentWorkTime}/>
                <PrivateRoute exact path="/agent/agentSharing" component={AgentSharing}/>
                <PrivateRoute exact path="/agent/agentSettings" component={AgentSettings}/>
                <Route component={NotFound}/>
            </Switch>
        </Provider>
    );

};
export default App;