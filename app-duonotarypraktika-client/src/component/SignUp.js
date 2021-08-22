import React, {Component} from 'react';
import {Switch, Route, BrowserRouter as Router, Link, BrowserRouter} from 'react-router-dom'
import axios from 'axios'

class SignUp extends Component {




    saveUser=(event)=>{
        event.preventDefault()
        axios({
            url:"http://localhost:/auth/saveClient",
            method:'post',
            data:{
                firstName:event.target[2]
            }
        })
    }
    render() {
        console.log(this.state)
        return (
            <div>
                <form onSubmit={this.showAlert} style={{boxShadow:"none",paddingTop:25}}>
                <div className="container ml-3">
                    <h1 style={{fontSize:37}} className="text-primary pb-4">Sign up</h1>
                    <div style={{width: 400}} className="row">
                        <div className="col-md-12 mt-4">
                            <input required className="mb-4" style={{height: 36, borderColor: "black"}} placeholder="Phone Number" type="password"/>
                            <input required className="mb-4" style={{height: 36, borderColor: "black"}} placeholder="Verify Code" type="password"/>
                            <input required className="mb-4" style={{height: 36, borderColor: "black"}} placeholder="First Name" type="text"/>
                            <input required className="mb-4" style={{height: 36, borderColor: "black"}} placeholder="Last Name" type="text"/>
                            <input required className="mb-4" style={{height: 36, borderColor: "black"}} placeholder="Email Address" type="text"/>
                            <input required className="mb-4" style={{height: 36, borderColor: "black"}} placeholder="Password" type="password"/>
                            <input required className="mb-4" style={{height: 36, borderColor: "black"}} placeholder="Confirm Password" type="password"/>
                            <input style={{width: 18, height: 18, marginRight: 10, marginTop: 6}}
                                   className="checkBox float-left mt-1" required type="checkbox"/>
                            <p style={{fontSize: 11, marginBottom: 25}}>Creating an account means you're okay with
                                our <a href="#">Terms of Service</a>,
                                <a href="#"> Privacy Police</a> and our default <a href="#"> Notification Settings</a>
                            </p>
                            <button className="btn btn-primary mb-5">Sign in</button>
                            <h6 style={{fontSize: 18}} className="text-center mb-3">Already have an account?</h6>
                            <h6 style={{fontSize: 18}} className="text-center">
                                <Route>
                                    <Link to={'signIn'}>
                                        Sign in
                                    </Link>
                                </Route>
                            </h6>


                        </div>
                    </div>


                </div>
                </form>
            </div>
        );
    }
}

export default SignUp;