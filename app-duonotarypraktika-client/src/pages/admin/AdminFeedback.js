import React, {Component} from 'react';
import AdminLayout from "../../component/AdminLayout";
import {connect} from 'react-redux'
import  'bootstrap/dist/css/bootstrap.css'

import * as FeedbackAction from "../../redux/actions/feedbackAction";


class AdminFeedback extends Component {

    componentDidMount() {
        this.props.getFeedbacks(1,10,false)
    }

    render() {

        const {feedbacks,feedbackCount,feedbackType,activeStyle} = this.props


        const switchFeedbackType=(type)=>{
            this.props.updateState({
                feedbackType:type
            })
            this.props.getFeedbacks(1,10,type)
        }


        // private String comment;
        // private String userEmail ;
        // private String userFullName ;
        // private String userComment;
        // private UUID feedbackId;
        // private boolean isCustomer;

        const sendEmailAnswerOfFeedback=(feedbackId,userEmail , userFullName , userComment)=>{
            let comment = document.getElementById('comment').value;
            this.props.sendFeedbackAnswer({comment:comment , userEmail:userEmail,userFullName:userFullName,userComment:userComment,feedbackId:feedbackId,isCustomer:feedbackType}).then(()=>{
                this.props.getFeedbacks(1,10,feedbackType)
            })


            document.getElementById('comment').value="";
        }


        return (
            <div>
                <AdminLayout pathname={this.props.location.pathname}>
                    <div className="row">
                        <div className="col-12 ml-2 mt-5">
                            <div className="row">
                                <div className="col-2">
                                    <h2 >Feedback</h2>
                                </div>

                                <div className="col-3 offset-2">
                                    <input style={{borderRadius:'20px'}} placeholder={'search...'} className={' pl-4 form-control'} type="text"/>
                                </div>
                            </div>

                            <div className="row mt-5">
                                <div className="col-3">
                                    {
                                        feedbackType===true?
                                            <div>
                                                <h6>Total customer feedbacks: {feedbackCount}</h6>
                                            </div>:<div>
                                                <h6>Total agent feedbacks: {feedbackCount}</h6>
                                            </div>
                                    }
                                </div>
                                <div className="col-4 offset-4">
                                        {
                                            feedbackType===false?
                                                <div className={'p-1 d-inline-block'} style={{borderRadius:'20px', backgroundColor:'#d6d6c2'}}>
                                                    <span  style = {activeStyle}   className={'d-inline-block ml-1  px-4 pb-1'} >Agent</span>
                                                    <span onClick={()=>switchFeedbackType(true)} className={'d-inline-block ml-3 px-4 pb-1'} style={{backgroundColor:'#d6d6c2',color:'#f5f5f0'}}>Customer</span>
                                                </div>:
                                                <div className={'p-1 d-inline-block'} style={{borderRadius:'20px', backgroundColor:'#d6d6c2'}}>
                                                    <span onClick={()=>switchFeedbackType(false)} style={{backgroundColor:'#d6d6c2',color:'#f5f5f0'}} className={'d-inline-block ml-1  px-4 pb-1'} >Agent</span>
                                                    <span style = {activeStyle} className={'d-inline-block ml-3 px-4 pb-1'} >Customer</span>
                                                </div>
                                        }
                                </div>
                            </div>


                            <div className={'ml-0'}>
                                {
                                    feedbacks.map((item,i)=>{
                                        return(
                                            <div key={i} className="row mt-1 pl-4 ">
                                                <div className="col-1 pb-3 bg-white pt-3">
                                                    <p className={'mt-2 mb-0'}>img</p>
                                                </div>
                                                <div className="col-2 pb-3 bg-white pt-3 " >
                                                    <p style={{fontSize:'10px'}} className={'mb-0 text-secondary'}>Agents:</p>
                                                    <h6>{item.agent}</h6>
                                                </div>

                                                <div style={{borderLeft: '1px solid gray', height: '50px'}} className={'mt-3'}/>

                                                <div className="col-2  pb-3 bg-white pt-3">
                                                    <p style={{fontSize:'10px'}} className={'mb-0 text-secondary'}>client:</p>
                                                    <h6 >{item.customer}</h6>
                                                </div>

                                                <div style={{borderLeft: '1px solid gray', height: '50px'}} className={'mt-3'}/>
                                                <div className="col-2 pb-3 bg-white pt-3">
                                                    <p style={{fontSize:'10px'}} className={'mb-0 text-secondary'}>{item.phoneNumber}</p>
                                                    <h6>{item.email}</h6>
                                                </div>

                                                <div style={{borderLeft: '1px solid gray', height: '50px'}} className={'mt-3'}/>
                                                <div className="col-1 pb-3 bg-white pt-3">
                                                    <p style={{fontSize:'10px'}} className={'mb-0 text-secondary'}>Amount:</p>
                                                    <h6>{item.amount}</h6>
                                                </div>

                                                <div style  ={{borderLeft: '1px solid gray', height: '50px'}} className={'mt-3'}/>

                                                <div className="col-1 pb-3 bg-white pt-3">
                                                    <p style={{fontSize:'10px'}} className={'mb-0 text-secondary'}>Rating:</p>
                                                    <h6>{item.rating}</h6>
                                                </div>


                                                <div style  ={{borderLeft: '1px solid gray', height: '50px'}} className={'mt-3'}/>

                                                <div className="col-2 bg-white ">
                                                    {
                                                        item.isAnswered?
                                                            <div>
                                                                <input disabled={true} className={'form-control'} placeholder={'comment...'} type="text"/>
                                                                <button disabled={true} className={'btn btn-success'}>Save</button>
                                                            </div>:
                                                            <div>
                                                                <input id={'comment'} className={'form-control'} placeholder={'comment...'} type="text"/>
                                                                {
                                                                    feedbackType===false?
                                                                        <button onClick={()=>sendEmailAnswerOfFeedback(item.id,item.email,item.agent,item.comment)} className={'btn btn-success'}>Save</button>:
                                                                        <button onClick={()=>sendEmailAnswerOfFeedback(item.id,item.email,item.customer,item.comment)} className={'btn btn-success'}>Save</button>
                                                                }
                                                            </div>

                                                    }
                                                </div>

                                                <div className="col-11 bg-white">
                                                    <p style={{fontSize:'14px'}} className={'mb-0 text-secondary'}>
                                                        {item.comment}
                                                    </p>
                                                </div>


                                            </div>
                                        )
                                    })
                                }
                            </div>
                        </div>
                    </div>


                </AdminLayout>
            </div>
        );
    }
}

AdminFeedback.propTypes = {};
const mapStateToProps = (state) =>{
    return{
        feedbacks: state.feedbacks.feedbacks,
        totalElements: state.feedbacks.totalElements,
        feedbackCount: state.feedbacks.feedbackCount,
        feedbackType: state.feedbacks.feedbackType,
        activeStyle: state.feedbacks.style,
    }
}

const mapDispatchToProps = (dispatch) => {
    return{
        getFeedbacks:(page,size,type)=>dispatch(FeedbackAction.getFeedbacks(page,size,type)),
        sendFeedbackAnswer:(payload)=>dispatch(FeedbackAction.sendFeedbackAnswer(payload)),
        updateState:(payload)=>dispatch(FeedbackAction.updateState(payload))
    }
}
export default connect(mapStateToProps,mapDispatchToProps)(AdminFeedback);