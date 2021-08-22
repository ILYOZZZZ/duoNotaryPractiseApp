import React, {Component} from 'react';
import {AvField, AvForm} from "availity-reactstrap-validation";
import {Button} from "reactstrap";
import {Link, withRouter} from "react-router-dom";
import {connect} from "react-redux";
import * as authActions from '../redux/actions/authActions'

class HomePage extends Component {
    render() {
        const {dispatch,history}=this.props
        const signIn=(e,v)=>{
            e.persist();
            e.preventDefault()
            console.log(v,"========")
            dispatch(authActions.login({v, history}));

        }
        return (
            <div>
                <h1>HomePage</h1>
                <h2>Sign In</h2>
                <AvForm onValidSubmit={signIn}>
                    <AvField
                        className="modal-input"
                        type="text"
                        name="phoneNumberOrEmail"
                        label="Enter username"
                        required
                        placeholder="Enter username"
                    />
                    <AvField
                        className="modal-input"
                        type="password"
                        name="password"
                        label="Enter password"
                        required
                        placeholder="Enter password"
                    />
                    <Button color="primary" type="submit" block>Sign in</Button>
                </AvForm>
            </div>
        );
    }
}


export default withRouter(connect(({auth: {loading}}) =>
    ({loading}))(HomePage))
