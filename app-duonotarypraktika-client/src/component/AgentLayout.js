import React, {Component} from 'react';
import {connect} from "react-redux";
import Row from "reactstrap/lib/Row";
import Col from "reactstrap/lib/Col";
import {Link} from "react-router-dom";

class AgentLayout extends Component {
    render() {
        const {loading, isAgent} = this.props;
        return (
            loading ?
                <div><h1>Loader</h1></div>
                :
                <div className="adminLayout-page">
                    {isAgent ?
                        <div className="main-layout">
                            <Row>
                                <Col md={2}>
                                    <div className="main-layout-left">
                                        <div style={{width: '130px', height: '60px', margin: 40}}>
                                            <img src="/assets/duo_no%201logo.png" alt="logo"/>

                                        </div>
                                        <div className="main-link-div">
                                            <Link to="/agent/agentMain"
                                                  className={
                                                      this.props.pathname === "/agent/agentMain" ?
                                                          "active-tab" : "default-tab"
                                                  }>
                                                <div className={
                                                    this.props.pathname === "/agent/agentMain" ?
                                                        "active-tab m-2" : "default-tab m-2"
                                                }>
                                                    <span style={{marginLeft: 25}}><img
                                                        src={this.props.pathname === "/agent/agentMain" ? "/assets/Vector.png" : "/assets/icons/Vector.svg"}/></span>

                                                    Main

                                                </div>
                                            </Link>
                                            <Link to="/agent/agentOrder"
                                                  className={
                                                      this.props.pathname === "/agent/agentOrder" ?
                                                          "active-tab" : "default-tab"
                                                  }>
                                                <div className={
                                                    this.props.pathname === "/agent/agentOrder" ?
                                                        "active-tab m-2" : "default-tab m-2"
                                                }>
                                                    <span style={{marginLeft: 25}}><img
                                                        src={this.props.pathname === "/agent/agentOrder" ? "/assets/Vector.png" : "/assets/icons/Vector.svg"}/></span>

                                                    Order

                                                </div>
                                            </Link>
                                            <Link to="/agent/agentSharing"
                                                  className={
                                                      this.props.pathname === "/agent/agentSharing" ?
                                                          "active-tab" : "default-tab"
                                                  }>
                                                <div className={
                                                    this.props.pathname === "/agent/agentSharing" ?
                                                        "active-tab m-2" : "default-tab m-2"
                                                }>
                                                    <span style={{marginLeft: 25}}><img
                                                        src={this.props.pathname === "/agent/agentSharing" ? "/assets/Vector.png" : "/assets/icons/Vector.svg"}/></span>

                                                    Sharing

                                                </div>
                                            </Link>

                                            <Link to="/agent/agentWorkTime"
                                                  className={
                                                      this.props.pathname === "/agent/agentWorkTime" ?
                                                          "active-tab" : "default-tab"
                                                  }>
                                                <div className={
                                                    this.props.pathname === "/agent/agentWorkTime" ?
                                                        "active-tab m-2" : "default-tab m-2"
                                                }>
                                                    <span style={{marginLeft: 25}}><img
                                                        src={this.props.pathname === "/agent/agentWorkTime" ? "/assets/Vector.png" : "/assets/icons/Vector.svg"}/></span>

                                                    Work Time

                                                </div>
                                            </Link>
                                            <Link to="/agent/agentSettings"
                                                  className={
                                                      this.props.pathname === "/agent/agentSettings" ?
                                                          "active-tab" : "default-tab"
                                                  }>
                                                <div className={
                                                    this.props.pathname === "/agent/agentSettings" ?
                                                        "active-tab m-2" : "default-tab m-2"
                                                }>
                                                    <span style={{marginLeft: 25}}><img
                                                        src={this.props.pathname === "/agent/agentSettings" ? "/assets/Vector.png" : "/assets/icons/Vector.svg"}/></span>

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

AgentLayout.propTypes = {};

export default connect(({
                            auth: {loading, isAgent}
                        }) =>
    ({isAgent, loading}))(AgentLayout);