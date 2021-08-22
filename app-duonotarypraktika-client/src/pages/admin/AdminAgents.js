import React, {Component} from 'react';
import PropTypes from 'prop-types';
import AdminLayout from "../../component/AdminLayout";
import Search from "../admin/maın/Search";
import '../../styles/main-page-style.scss'

class AdminAgents extends Component {
    render() {
        return (
            <div>
                <AdminLayout pathname={this.props.location.pathname}>
                    <div className={'p-5'}>
                        <div className="row">
                            <div className="col-md-2">
                                <h3><b>Agents</b></h3>
                            </div>
                            <div className="col-md-4 offset-3">
                                <Search/>
                            </div>
                        </div>

                        <div className="row pt-5">
                            <div className="col-md-12">
                                <div className="card card3">
                                    <div className="card-body align-items-baseline">
                                        <div className="row">
                                            <div className="col-md-1 mr-1">
                                                <img style={{width: 63, height: 63}} src="/assets/Ellipse 10as.svg"
                                                     alt=""/>
                                            </div>
                                            <div className="wrapper pr-0 pl-0 border-right">
                                                <p style={{width:230}} className="mb-0 mt-2 mr-0">Agent</p>
                                                <h5 style={{width:230}} className="mr-0">Cameron Williams</h5>
                                            </div>
                                            <div className="wrapper-two ml-4 border-right">
                                                <p className="mb-2 mt-2">Location (2 min ago)</p>
                                                <h6 style={{fontSize:14}}>3517W.Gray St,Pennsylvania 57867</h6>
                                            </div>
                                           <div className="col-md-2 ml-2 border-right">
                                               <p className="mb-1 mt-2">Online time</p>
                                               <h6>5:20</h6>
                                           </div>
                                            <div className="col-md-2">
                                                <p className="mb-1 mt-2 ml-2">Orders</p>
                                                <h6 className="ml-2">22</h6>
                                            </div>
                                            <button className="btn"><svg width="8" height="14" viewBox="0 0 8 14" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                <path d="M5.17168 6.99999L0.22168 2.04999L1.63568 0.635986L7.99968 6.99999L1.63568 13.364L0.22168 11.95L5.17168 6.99999Z" fill="#313E47"/>
                                            </svg></button>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </AdminLayout>
            </div>
        );
    }
}

AdminAgents.propTypes = {};

export default AdminAgents;