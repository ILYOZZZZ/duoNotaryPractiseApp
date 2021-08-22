import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from "react-redux";
import Row from "reactstrap/lib/Row";
import Col from "reactstrap/lib/Col";
import {Link} from "react-router-dom";
import Collapse from "reactstrap/lib/Collapse";
import '../styles/admin-layout-style.scss'

class ClientLayout extends Component {
    render() {
        const {loading,isUser} = this.props
        return (
            loading ?
                <div><h1>Loader</h1></div>
                :
                <div className="adminLayout-page">
                    {isUser ?
                        <div className="main-layout">
                            <Row>
                                <Col md={2}>
                                    <div className="main-layout-left">
                                        <div style={{width: '130px', height: '60px', margin: 40}}>
                                            <img src="/assets/duo_no%201logo.png" alt="logo"/>

                                        </div>
                                        <div className="main-link-div">
                                            <Link to="/client/clientMain"
                                                  className={
                                                      this.props.pathname === "/client/clientMain" ?
                                                          "active-tab" : "default-tab"
                                                  }>
                                                <div className={
                                                    this.props.pathname === "/client/clientMain" ?
                                                        "active-tab m-2" : "default-tab m-2"
                                                }>
                                                    <span style={{marginLeft: 25}}><img
                                                        src={this.props.pathname === "/client/clientMain" ? "/assets/Vector.png" : "/assets/icons/Vector.svg"}/></span>

                                                    Main

                                                </div>
                                            </Link>
                                            <Link to="/client/clientOrder"
                                                  className={
                                                      this.props.pathname === "/client/clientOrder" ?
                                                          "active-tab" : "default-tab"
                                                  }>
                                                <div className={
                                                    this.props.pathname === "/client/clientOrder" ?
                                                        "active-tab m-2" : "default-tab m-2"
                                                }>
                                                    <span style={{marginLeft: 25}}><img
                                                        src={this.props.pathname === "/client/clientOrder" ? "/assets/Vector.png" : "/assets/icons/Vector.svg"}/></span>

                                                    Order

                                                </div>
                                            </Link>
                                            <Link to="/client/clientSharing"
                                                         className={
                                                             this.props.pathname === "/client/clientSharing" ?
                                                                 "active-tab" : "default-tab"
                                                         }>
                                            <div className={
                                                this.props.pathname === "/client/clientSharing" ?
                                                    "active-tab m-2" : "default-tab m-2"
                                            }>
                                                    <span style={{marginLeft: 25}}><img
                                                        src={this.props.pathname === "/client/clientSharing" ? "/assets/Vector.png" : "/assets/icons/Vector.svg"}/></span>

                                                Sharing

                                            </div>
                                        </Link>
                                            <Link to="/client/clientSettings"
                                                     className={
                                                         this.props.pathname === "/client/clientSettings" ?
                                                             "active-tab" : "default-tab"
                                                     }>
                                            <div className={
                                                this.props.pathname === "/client/clientSettings" ?
                                                    "active-tab m-2" : "default-tab m-2"
                                            }>
                                                    <span style={{marginLeft: 25}}><img
                                                        src={this.props.pathname === "/client/clientSettings" ? "/assets/Vector.png" : "/assets/icons/Vector.svg"}/></span>

                                                Settings

                                            </div>
                                        </Link>



                                        </div>
                                    </div>
                                </Col>
                                <Col md={10}>
                                    {/*Right side*/}
                                    <div className="main-layout-right">
                                        {this.props.children}
                                    </div>
                                </Col>
                            </Row>


                        </div>
                        : ''}
                </div>
        );
    }
}

ClientLayout.propTypes = {};

export default connect(({
                            auth: {loading, isUser}
                        }) =>
    ({isUser, loading}))(ClientLayout);