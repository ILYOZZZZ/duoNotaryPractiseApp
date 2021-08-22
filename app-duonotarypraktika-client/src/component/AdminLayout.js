import React, {Component} from 'react';
import {Link} from "react-router-dom";
import {connect} from "react-redux";
import Row from "reactstrap/lib/Row";
import Col from "reactstrap/lib/Col";
import Collapse from "reactstrap/lib/Collapse";
import Loader from "./Loader";

class AdminLayout extends Component {
    constructor(props) {
        super(props);
        this.state = {
            text: "",
            showModal: false,
            path: '',
        };
    }

    // openModal = (text, path) => {
    //     this.setState({text: text, path: path, showModal: !this.state.showModal})
    // }

    render() {
        const {dispatch, isAdmin, isSuperAdmin, loading, isOpen} = this.props;
        const showServices = () => {
            dispatch({
                type: 'updateState',
                payload: {
                    isOpen: !isOpen
                }
            })
        }
        return (
            loading ?
                <Loader/>
                :
                <div className="admin-layout-page">
                    {isSuperAdmin || isAdmin ?
                        <div className="main-layout">
                            <Row>
                                <Col md={2}>
                                    <div className="main-layout-left">
                                        <div style={{width: '130px', height: '60px', margin: 40}}>
                                            <img src="/assets/duo_no%201logo.png" alt="logo"/>

                                        </div>
                                        <div className="main-link-div">
                                            <Link to="/admin/adminMain"
                                                  className={
                                                      this.props.pathname === "/admin/adminMain" ?
                                                          "active-tab" : "default-tab"
                                                  }>
                                                <div className={
                                                    this.props.pathname === "/admin/adminMain" ?
                                                        "active-tab m-2" : "default-tab m-2"
                                                }>
                                                    <span style={{marginLeft: 25}}><img
                                                        src={this.props.pathname === "/admin/adminMain" ? "/assets/Vector.png" : "/assets/icons/Vector.svg"}/></span>

                                                    Main

                                                </div>
                                            </Link>
                                            <Link to="/admin/agents"
                                                  className={
                                                      this.props.pathname === "/admin/agents" ?
                                                          "active-tab" : "default-tab"
                                                  }>
                                                <div className={
                                                    this.props.pathname === "/admin/agents" ?
                                                        "active-tab m-2" : "default-tab m-2"
                                                }>
                                                    <span style={{marginLeft: 25}}><img
                                                        src={this.props.pathname === "/admin/agents" ? "/assets/Vector.png" : "/assets/icons/Vector.svg"}/></span>

                                                    Agents

                                                </div>
                                            </Link>
                                            <div onClick={showServices}>
                                                <Link to="/admin/service"
                                                      className={
                                                          this.props.pathname === "/admin/service" ?
                                                              "active-tab" : "default-tab"
                                                      }>
                                                    <div className={
                                                        this.props.pathname === "/admin/service" ?
                                                            "active-tab m-2" : "default-tab m-2"
                                                    }>
                                                    <span style={{marginLeft: 25}}><img
                                                        src={this.props.pathname === "/admin/service" ? "/assets/Vector.png" : "/assets/icons/Vector.svg"}/></span>

                                                        Service

                                                    </div>
                                                </Link>
                                            </div>
                                            <Collapse isOpen={isOpen}>
                                                <Link to="/admin/service"
                                                      className={
                                                          this.props.pathname === "/admin/service" ?
                                                              "active-tab" : "default-tab"
                                                      }>
                                                    <div className={
                                                        this.props.pathname === "/admin/service" ?
                                                            "active-tab m-2" : "default-tab m-2"
                                                    }>

                                                        Service

                                                    </div>
                                                </Link>
                                                <Link to="/admin/servicePrice"
                                                      className={
                                                          this.props.pathname === "/admin/servicePrice" ?
                                                              "active-tab" : "default-tab"
                                                      }>
                                                    <div className={
                                                        this.props.pathname === "/admin/servicePrice" ?
                                                            "active-tab m-2" : "default-tab m-2"
                                                    }>

                                                        ServicePrice

                                                    </div>
                                                </Link>
                                                <Link to="/admin/mainService"
                                                      className={
                                                          this.props.pathname === "/admin/mainService" ?
                                                              "active-tab" : "default-tab"
                                                      }>
                                                    <div className={
                                                        this.props.pathname === "/admin/mainService" ?
                                                            "active-tab m-2" : "default-tab m-2"
                                                    }>

                                                        Main Service

                                                    </div>
                                                </Link>
                                                <Link to="/admin/additionalService"
                                                      className={
                                                          this.props.pathname === "/admin/additionalService" ?
                                                              "active-tab" : "default-tab"
                                                      }>
                                                    <div className={
                                                        this.props.pathname === "/admin/additionalService" ?
                                                            "active-tab m-2" : "default-tab m-2"
                                                    }>

                                                        Additional Service

                                                    </div>
                                                </Link>
                                                <Link to="/admin/additionalServicePrice"
                                                      className={
                                                          this.props.pathname === "/admin/additionalServicePrice" ?
                                                              "active-tab" : "default-tab"
                                                      }>
                                                    <div className={
                                                        this.props.pathname === "/admin/additionalServicePrice" ?
                                                            "active-tab m-2" : "default-tab m-2"
                                                    }>

                                                        Additional Service Price

                                                    </div>
                                                </Link>
                                                <Link to="/admin/pricing"
                                                      className={
                                                          this.props.pathname === "/admin/pricing" ?
                                                              "active-tab" : "default-tab"
                                                      }>
                                                    <div className={
                                                        this.props.pathname === "/admin/pricing" ?
                                                            "active-tab m-2" : "default-tab m-2"
                                                    }>

                                                        Pricing

                                                    </div>
                                                </Link>
                                                <Link to="/admin/mainServiceWorkTime"
                                                      className={
                                                          this.props.pathname === "/admin/mainServiceWorkTime" ?
                                                              "active-tab" : "default-tab"
                                                      }>
                                                    <div className={
                                                        this.props.pathname === "/admin/mainServiceWorkTime" ?
                                                            "active-tab m-2" : "default-tab m-2"
                                                    }>

                                                        Main Service Work Time

                                                    </div>
                                                </Link>

                                            </Collapse>
                                                <Link to="/admin/zipCodes"
                                                      className={
                                                          this.props.pathname === "/admin/zipCodes" ?
                                                              "active-tab" : "default-tab"
                                                      }>
                                                    <div className={
                                                        this.props.pathname === "/admin/zipCodes" ?
                                                            "active-tab m-2" : "default-tab m-2"
                                                    }>
                                                    <span style={{marginLeft: 25}}><img
                                                        src={this.props.pathname === "/admin/zipCodes" ? "/assets/Vector.png" : "/assets/icons/Vector.svg"}/>
                                                    </span>
                                                        ZipCodes
                                                    </div>
                                                </Link>
                                            <Link to="/admin/orders"
                                                  className={
                                                      this.props.pathname === "/admin/orders" ?
                                                          "active-tab" : "default-tab"
                                                  }>
                                                <div className={
                                                    this.props.pathname === "/admin/orders" ?
                                                        "active-tab m-2" : "default-tab m-2"
                                                }>
                                                    <span style={{marginLeft: 25}}><img
                                                        src={this.props.pathname === "/admin/orders" ? "/assets/Vector.png" : "/assets/icons/Vector.svg"}/></span>

                                                    Orders

                                                </div>
                                            </Link>
                                            <Link to="/admin/feedback"
                                                  className={
                                                      this.props.pathname === "/admin/feedback" ?
                                                          "active-tab" : "default-tab"
                                                  }>
                                                <div className={
                                                    this.props.pathname === "/admin/feedback" ?
                                                        "active-tab m-2" : "default-tab m-2"
                                                }>
                                                    <span style={{marginLeft: 25}}><img
                                                        src={this.props.pathname === "/admin/feedback" ? "/assets/Vector.png" : "/assets/icons/Vector.svg"}/></span>

                                                    Feedback

                                                </div>
                                            </Link>
                                            <Link to="/admin/discount"
                                                  className={
                                                      this.props.pathname === "/admin/discount" ?
                                                          "active-tab" : "default-tab"
                                                  }>
                                                <div className={
                                                    this.props.pathname === "/admin/discount" ?
                                                        "active-tab m-2" : "default-tab m-2"
                                                }>
                                                    <span style={{marginLeft: 25}}><img
                                                        src={this.props.pathname === "/admin/discount" ? "/assets/Vector.png" : "/assets/icons/Vector.svg"}/></span>

                                                    Discount

                                                </div>
                                            </Link>
                                            <Link to="/admin/customer"
                                                  className={
                                                      this.props.pathname === "/admin/customer" ?
                                                          "active-tab" : "default-tab"
                                                  }>
                                                <div className={
                                                    this.props.pathname === "/admin/customer" ?
                                                        "active-tab m-2" : "default-tab m-2"
                                                }>
                                                    <span style={{marginLeft: 25}}><img
                                                        src={this.props.pathname === "/admin/customer" ? "/assets/Vector.png" : "/assets/icons/Vector.svg"}/></span>

                                                    Customer

                                                </div>
                                            </Link>
                                            <Link to="/admin/blog"
                                                  className={
                                                      this.props.pathname === "/admin/blog" ?
                                                          "active-tab" : "default-tab"
                                                  }>
                                                <div className={
                                                    this.props.pathname === "/admin/blog" ?
                                                        "active-tab m-2" : "default-tab m-2"
                                                }>
                                                    <span style={{marginLeft: 25}}><img
                                                        src={this.props.pathname === "/admin/blog" ? "/assets/Vector.png" : "/assets/icons/Vector.svg"}/></span>

                                                    Blog

                                                </div>
                                            </Link>
                                            <Link to="/admin/settings"
                                                  className={
                                                      this.props.pathname === "/admin/settings" ?
                                                          "active-tab" : "default-tab"
                                                  }>
                                                <div className={
                                                    this.props.pathname === "/admin/settings" ?
                                                        "active-tab m-2" : "default-tab m-2"
                                                }>
                                                    <span style={{marginLeft: 25}}><img
                                                        src={this.props.pathname === "/admin/settings" ? "/assets/Vector.png" : "/assets/icons/Vector.svg"}/></span>

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
                        : null}
                </div>
        );
    }
}

AdminLayout.propTypes = {};

export default connect(({
                            auth: {loading, isAdmin, isSuperAdmin, isOpen}
                        }) =>
    ({isAdmin, isSuperAdmin, loading, isOpen}))(AdminLayout);