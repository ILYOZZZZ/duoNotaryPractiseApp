import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.css'
import logoVertical from "../images/logo-vertical.png";
import logo from "../images/menu bar.png";
import back from "../images/arrow-left.png"
import rightUp from "../images/linkwhite.png";
import {AvField, AvForm} from "availity-reactstrap-validation";


class OtherCustomersSay extends Component {

    opensignin =()=> {
        document.getElementById("sign-in").style.width = "450px";
        this.closesignup();
    }

    closesignin =()=> {
        document.getElementById("sign-in").style.width = "0";
    }
    opensignup =()=> {
        this.closesignin();
        document.getElementById("sign-up").style.width = "450px";
    }

    closesignup =()=> {
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
                    <div className="col-md-8 mt-5"><a href="/" className="d-flex backHome">
                        <img src={back} width="70px" className="backImg" alt="#"/>
                        <h4><span
                            className="lightGray">BACK TO HOME</span></h4></a>
                    </div>
                    <div className="col-md-4 pl-5 pr-0 text-right mt-5 ">
                        <h1 className="timeRight"><span className="lightGray">NEW YORK <br/></span> 09:11AM </h1>
                    </div>
                </div>
                <div className="row allRows">
                    <div className="col-md-8 mt-5">
                        <h1><span
                            className="lightGray">WHAT OUR CUSTOMERS</span>
                            <br/> HAVE TO SAY</h1>
                    </div>
                    <div className="col-md-4 pl-5 pr-0 text-right mt-5">
                        <a onClick={this.opensignin} className="link getInTouch"> <img src={rightUp} width="25px"
                                                                            className="margin-left colorDark"
                                                                            alt="#"/> <br/><strong>GET IN
                            TOUCH<br/> WITH US
                            NOW</strong></a>
                    </div>
                </div>
                <div className="row allRows mt-5">
                    <h1>Ilyos's team work will be here. (feedbacks)</h1>
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

export default OtherCustomersSay;