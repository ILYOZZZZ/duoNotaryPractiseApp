import React, {Component} from 'react';
import axios from 'axios'
import './Home.scss'
import logo from "./images/menu bar.png"
import rightUp from "./images/linkwhite.png"
import firstPic from "./images/pic1.png"
import secondPic from "./images/pic2.png"
import logoVertical from "./images/logo-vertical.png"
import * as authActions from '../../redux/actions/authActions'

import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import {Col, Modal, ModalBody, ModalFooter} from "reactstrap";
import {AvField, AvForm} from "availity-reactstrap-validation";
import {connect} from "react-redux";

class Home extends Component {

    opensignin = () => {
        document.getElementById("sign-in").style.width = "450px";
        this.closesignup();
    }

    closesignin = () => {
        document.getElementById("sign-in").style.width = "0";
    }
    opensignup = () => {
        this.closesignin();
        document.getElementById("sign-up").style.width = "450px";
    }

    closesignup = () => {
        document.getElementById("sign-up").style.width = "0";
    }
    handleVideo1 = () => {
        var s = '';
        s = ` <video width="710" height="412" controls>
                    <source src="../../../../../../../../tesy.mp4" type="video/mp4" />
                </video> `
        document.getElementById("video1").innerHTML = s
    }
    handleVideo2 = () => {
        var s = '';
        s = ` <video width="710" height="412" controls>
                    <source src="./images/test.mp4" type="video/mp4" />
                </video> `
        document.getElementById("video2").innerHTML = s
    }

    render() {
        const {dispatch,history}=this.props
        const getLogin=(e,v)=>{
            e.preventDefault()
            console.log(v,";;;;;;;;;;;;;;")
            dispatch(authActions.login({v,history}))
        }
        return (
            <div>
                <div>
                    <div className="side-menu">
                        <a href="#"><img src={logoVertical} alt="#"/></a>
                        <a href="#"><img className="menuBarLogo" src={logo} alt="#"/></a>
                    </div>
                    <div className="row allRows">
                        <div className="col-md-8 mt-5">
                            <h1><span
                                className="lightGray">YOU CAN BEGIN YOUR <br/> NOTARIZATION OR APOSTILLE</span>
                                <br/> AT ANY TIME CONVENIENT <br/> FOR YOU.</h1>
                        </div>
                        <div className="col-md-4 pl-5 pr-0 text-right mt-5">
                            <h1><span className="lightGray">NEW YORK <br/></span> 09:11AM </h1>
                        </div>
                    </div>
                    <div className="row allRows">
                        <div className="col-md-4 mt-3">
                            <a href="/schedule" className="link">SCHEDULE AN APPOINTMENT ></a>
                        </div>
                        <div className="col-md-5 mt-3">
                            <a href="/othersSay" className="link"><span className="text-dark ml-5"><strong>WHAT OUR CUSTOMERS HAVE TO SAY  >></strong></span></a>
                        </div>
                        <div className="col-md-2 text-right offset-1">
                            <a onClick={this.opensignin} className="link"> <img src={rightUp} width="25px"
                                                                                className="margin-left colorDark"
                                                                                alt="#"/> <br/><strong>GET IN
                                TOUCH<br/> WITH US
                                NOW</strong></a>
                        </div>
                    </div>
                    <div className="row img-row">
                        <div className="col-md-6 mr-0 mb-1 pr-0 mt-2">
                            <div className="card" id="video1">
                                <img src={firstPic} width="710px" height="412px" alt="#"/>
                                <div className="card-overlay">
                                    <div className="arrowStyle">
                                        <img src={rightUp} className="card-overlay-img img-fluid" alt="#"/>
                                    </div>
                                    <h5>REMOTE NOTARIZATION</h5>
                                    <a href="#" className="watch" onClick={this.handleVideo1}><strong>WATCH NOW</strong></a>
                                </div>
                            </div>
                        </div>
                        <div className="col-md-6 ml-0 pl-0 mb-1 mt-2">
                            <div className="card" id="video2">
                                <img src={secondPic} width="710px" height="412px" alt="#"/>
                                <div className="card-overlay card-overlay-right">
                                    <div className="arrowStyle">
                                        <img src={rightUp} className="card-overlay-img img-fluid" alt="#"/>
                                    </div>
                                    <h5>MOBILE NOTARIZATION</h5>
                                    <h6><a href="#" className="watch" onClick={this.handleVideo2}><strong>WATCH
                                        NOW</strong></a></h6>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="sign-in" id="sign-in">
                        <a className="closebtn" onClick={this.closesignin}>&times;</a>
                        <h1 className="text-center text-info p-0 mt-3 mb-0">Sign in</h1>
                        <AvForm onValidSubmit={getLogin}>
                            <AvField type='text' name='phoneNumberOrEmail' className="input"
                                     label='Email address' required/>
                            <AvField type='password' name='password' className="input"
                                     label='Password' required/>
                            <a href="#" className="link"><span
                                className="lightGray forget-password">Forget password?</span></a><br/>
                            <button type="submit" className="btn mb-4 mt-4 btn-info submitBtn">Sign in</button>
                            <p className="text-center"><span className="align lightGray">If feeling lazy?</span></p>
                            {/*<div className="d-flex">*/}
                            {/*    <div className="col-md-6 ml-2">*/}
                            {/*        <button className="btn btn-outline border rounded">SigninwithFacebook</button>*/}
                            {/*    </div>*/}
                            {/*    <div className="col-md-6">*/}
                            {/*        <button className="btn btn-outline border rounded">Sign in with Google</button>*/}
                            {/*    </div>*/}
                            {/*</div>*/}
                            <div className="d-block text-center">
                                <h4 className="margin-right">Don't have an account?</h4> <br/>
                                <a href="#" className="link span" onClick={this.opensignup}><strong>Sign up</strong></a>
                            </div>
                        </AvForm>
                    </div>
                    <div className="sign-up" id="sign-up">
                        <AvForm onSubmit={"#"}>
                            <a className="closebtn" onClick={this.closesignup}>&times;</a>
                            <h1 className="text-center text-info p-0 mt-3 mb-0">Sign up</h1>
                            <AvField type='text' name='fullName'
                                     label='Full name' required/>
                            <AvField type='text' name='email'
                                     label='Email address' required/>
                            <AvField type='password' name='password'
                                     label='Password' required/>
                            <AvField type='password' name='repeatPassword'
                                     label='Repeat password' required/>
                            <div className="d-flex p-2">
                                <input type="checkbox" name="confirm" value="check" id="confirm"/> <p
                                className="info">Creating an account says that you're okay with our <a
                                href="#"> Terms of Service, Privacy Policy</a> and our default <a href="#"> Notification
                                Settings</a></p>
                            </div>
                            <button type={'submit'} className="btn mb-4 btn-info submitBtn">Sign up</button>
                            <div className="d-block text-center">
                                <h4 className="margin-right">Already have an account?</h4> <br/>
                                <a href="#" className="link span pb-4" onClick={this.opensignin}><strong>Sign in</strong></a>
                            </div>
                        </AvForm>
                    </div>
                </div>
            </div>
        );
    }
}
Home.propTypes={}
export default connect(({
                            auth: {loading, isAdmin, isSuperAdmin, isOpen}
                        }) =>
    ({isAdmin, isSuperAdmin, loading, isOpen}))(Home);