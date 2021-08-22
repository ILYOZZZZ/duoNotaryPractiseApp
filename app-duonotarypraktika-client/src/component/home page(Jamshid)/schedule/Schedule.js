import React, {Component} from 'react';
import logoVertical from "../images/logo-vertical.png";
import logo from "../images/menu bar.png";
import rightUp from "../images/right up.svg";
import {AvField, AvForm} from "availity-reactstrap-validation";
import firstPic from "../images/pic1.png";
import secondPic from "../images/pic2.png";

class Schedule extends Component {

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

    render() {
        return (
            <div>
                <div className="side-menu">
                    <a href="#"><img src={logoVertical} alt="#"/></a>
                    <a href="#"><img className="menuBarLogo" src={logo} alt="#"/></a>
                </div>
                <div className="row allRows">
                    <div className="col-md-6 mt-5">
                        <h1>YOU CAN BEGIN YOUR NOTARIZATION OR APOSTILLE AT ANY TIME CONVENIENT FOR YOU.</h1>
                    </div>
                    <div className="col-md-4 offset-2 text-right mt-5">
                        <a onClick={this.opensignin} className="link size">
                            <img src={rightUp} width="50px" className="margin-left" alt="#"/> <br/>GET IN
                            TOUCH<br/> WITH US NOW</a>
                    </div>
                </div>
                <div className="row allRows">
                    <div className="col-md-2 mt-5">
                        <a href="/" className="link"><strong>WATCH VIDEO ></strong></a>
                    </div>
                    <div className="col-md-2 mt-5 mb-3 offset-8">
                        <a href="/othersSay" className="link">
                            WHAT OUR CUSTOMERS HAVE TO SAY
                        </a>
                    </div>
                </div>
                <div className="row img-row mb-2">
                    <div className="col-md-4 mr-5 pr-0 mt-3 d-flex">
                        <div className="card ml-1">
                            <img src={firstPic} width="550px" alt="#"/>
                            <div className="overlay">
                                {/*<img src={rightUp} className="overlay-img" width="50px" alt="#"/>*/}
                                <h5>REMOTE NOTARIZATION</h5>
                                <h6><a href="#" className="watch"><strong>SCHEDULE AN APPOINTMENT</strong></a></h6>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-4 ml-4 pl-0 mt-3">
                        <div className="card">
                            <img src={secondPic} width="550px" alt="#"/>
                            <div className="overlay">
                                {/*<img src={rightUp} className="overlay-img" width="50px" alt="#"/>*/}
                                <h5>MOBILE NOTARIZATION</h5>
                                <h6><a href="#" className="watch"><strong>SCHEDULE AN APPOINTMENT</strong></a></h6>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-2 third-col d-block">
                        <div>
                            <a href="#" className="link third-col-link"><strong>MOBILE<br/> NOTARIZATION</strong></a>
                        </div>
                        <div className="second-link">
                            <a href="#" className="link third-col-link">APOSTLE</a>
                        </div>
                        <div className="third-link">
                            <a href="#" className="link third-col-link">1-9 VERIFICATION</a>
                        </div>
                        <div className="fourth-link">
                            <a href="#" className="link third-col-link">EMBASSY<br/>LEGALIZATION</a>
                        </div>
                    </div>
                </div>


                <div className="sign-in" id="sign-in">
                    <a className="closebtn" onClick={this.closesignin}>&times;</a>
                    <h1 className="text-center text-info p-0 mt-3 mb-0">Sign in</h1>
                    <AvForm onSubmit={"#"}>
                        <AvField type='text' name='email' className="input"
                                 label='Email address' required/>
                        <AvField type='password' name='password' className="input"
                                 label='Password' required/>
                        <a href="#" className="link"><span
                            className="lightGray forget-password">Forget password?</span></a><br/>
                        <button type={'submit'} className="btn mb-4 mt-4 btn-info submitBtn">Sign in</button>
                        <p className="text-center"><span className="align lightGray">If feeling lazy?</span></p>
                        <div className="d-flex">
                            <div className="col-md-6 ml-2">
                                <button className="btn btn-outline border rounded">SigninwithFacebook</button>
                            </div>
                            <div className="col-md-6">
                                <button className="btn btn-outline border rounded">Sign in with Google</button>
                            </div>
                        </div>
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
        );
    }
}

export default Schedule;