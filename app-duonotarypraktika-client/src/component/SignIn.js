import React, {Component} from 'react';
import axios from 'axios'
import  {Switch, Route, BrowserRouter as Router, Link} from 'react-router-dom'
import { InputGroup, InputGroupText, InputGroupAddon } from 'reactstrap';
import {BeatLoader} from "react-spinners";
class SignIn extends Component {

    constructor(props) {
        super(props);
        this.state = ({
            data: [],
            loading:false
        })
    }


    SignIn=(event)=>{
       event.preventDefault()
        axios({
            url:'http://localhost:/auth/login',
            method:'post',
            data:{
                phoneNumberOrEmail:event.target[0].value,
                password:event.target[1].value
            }
        }).then(res => {
            this.setState({
                loading: !this.state.loading,
            })
        })
    }


    render() {
        const {loading} = this.state
        return (
            <div>
                <div className="container pt-5">
                    {loading?<div style={{marginTop: 200,}} className="container"><BeatLoader size={20} loading={true}
                                                                                               color='blue'/></div>:
                        <div className="row mt-4">
                            <form style={{boxShadow:"none",width:500}} onSubmit={this.SignIn}>
                                <div className="col-md-12 mt-3">
                                    <h4 className="text-center">Sign In Notary</h4>
                                    <input required style={{borderRadius:0,height:55}} className="bg-light w-100 mt-3" type="text" name="PhoneNumberOrEmail " placeholder="PhoneNumber Or Email Adress"/>
                                    <input required style={{borderRadius:0,height:55}} className="bg-light w-100 mt-3 d-flex" type="password" name="password" placeholder="Password"/>
                                    <button style={{height:50}} className="btn-group-sm btn-primary mt-3">Sign in</button>
                                </div>
                            </form>
                        </div>
                    }
                </div>
            </div>
        );
    }
}

export default SignIn;