import React, {Component} from 'react';
import AdminLayout from "../../component/AdminLayout";
import Modal from 'react-modal';

import {connect} from 'react-redux'
import 'bootstrap/dist/css/bootstrap.css'


import AsyncSelect from 'react-select/async';
import Select from 'react-select/';


import * as Yup from 'yup'
import { Formik, Field, Form, ErrorMessage } from 'formik';

import * as ZipCodeCurrentAction from '../../redux/actions/zipCodeCurrentAction'
import {getCountiesOfState,getStates} from '../../redux/actions/countyAction'

class ZipCodeCurrent extends Component {

    componentDidMount() {
        this.props.getCurrentZipCode(this.props.currentId)
    }
    render() {

        const {counties,states,agents,workHours,zipCode,countyName,customStyles,modalActive,modalType , agentToLink,agentsToLink,selectedState,selectedCounty} = this.props

        const setCurrentValuesToInput = () =>{
            //states , counties , selectedCounty

            this.props.getCurrentValuesOfZip(countyName.id,zipCode.id,countyName.name.substring(0,2))

            this.props.updateState({
                selectedState:  {label:countyName.name , value:countyName.id}
            })
        }





        const closeModal = () =>{
            this.props.updateState({
                modalActive:false
            })
        }

        const drawerState = (states,opt) =>{
            if(opt){
                let statesToDraw = []
                states.map(item=>{
                    statesToDraw.push({label:item.firstName + ' ' +  item.lastName + ',' + item.address , value:item.id})
                })
                return statesToDraw
            }else{
                let statesToDraw = []
                states.map(item=>{
                    statesToDraw.push({label:item.name , value:item.id})
                })
                return statesToDraw
            }
        }

        const removeAgent = (id)=>{
            this.props.deleteAgent(id).then(this.props.getCurrentZipCode(zipCode.id))
        }


        const editZipCodeFields = (values) =>{
            this.props.editZipCodeFields(values).then(()=>{
                this.props.getCurrentZipCode(zipCode.id)
                this.props.updateState({
                    modalActive:false,
                    modalType:''
                })
            })
        }

        const addAgentsToZipCode=()=>{
            this.props.getAgentsOfZipCodeToLink()
        }

        const setCurrentAgentToState = agent =>{
            this.props.updateState({
                agentToLink:agent
            })
        }

        const saveAgent = () =>{
            this.props.saveAgent().then(this.props.getCurrentZipCode(zipCode.id)).then(()=>{
                this.props.getCurrentZipCode(zipCode.id)
                this.props.updateState({
                    modalActive:false,
                    modalType:''
                })
            })
        }

        const handleChangeState = selectedState =>{
            this.props.updateState({
                selectedCounty:{},
                selectedState:selectedState
            })
            this.props.getCountiesOfState(1,"",selectedState.value,false).then((res)=>{
                this.props.updateState({
                    counties:res.payload.object.content
                })
            })
        }


        const handleChangeCounty = selectedCounty =>{
            this.props.updateState({
                selectedCounty:selectedCounty
            })
        }

        const loadOptionsState = (inputValue, callback) => {
            this.props.getStates(inputValue,1,10,false).then((response)=>{
                let states = drawerState(response.payload.object.content,false);
                callback(states)
            })



        };

        return (
            <AdminLayout pathname={this.props.location.pathname}>
                <div className="row  ">
                    <div className="col-12 bg-light pt-5 ">
                        <div className="row ml-3">
                            <div className="col-4">
                                <h2 className={'d-inline-block mt-2'}>{countyName.name + ' - ' + zipCode.name} </h2>

                                <svg  width="2.5em" height="2.5em" viewBox="0 0 16 16" className="bi bi-pencil d-inline-block mb-2 ml-2 bg-white p-2" style={{borderRadius:'50%'}}
                                      fill="red" xmlns="http://www.w3.org/2000/svg">
                                    <path
                                        d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                    <path fillRule="evenodd"
                                          d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                </svg>

                                <svg onClick={setCurrentValuesToInput} width="2.5em" height="2.5em" viewBox="0 0 16 16" className="bi bi-pencil d-inline-block mb-2 ml-2 bg-white p-2" style={{borderRadius:'50%'}}
                                     fill="red" xmlns="http://www.w3.org/2000/svg">
                                    <path fillRule="evenodd"
                                          d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                </svg>

                            </div>
                            <div className="col-3 offset-3 ">
                                <input style={{borderRadius:'20px'}}   placeholder={'Search'} type="text" className={'form-control pl-4 mt-3'}/>
                            </div>
                        </div>
                        <div className="row mt-5 pl-4">
                            <div className="col-12 ">
                                <h4>Work Hours</h4>
                            </div>
                            {
                                workHours.map(item=>{
                                    return(
                                        <div className={'col-5   bg-white p-3 ml-3'} style={{borderRadius:'10px'}}>
                                            <div>
                                                {
                                                    item.serviceName?
                                                        <p className={'d-inline-block mb-0'}>ONLINE</p>:
                                                        <p className={'d-inline-block mb-0'}>IN-PERSON</p>
                                                }
                                                <p className={'d-inline-block mb-0 ml-5 pl-5'}>
                                                    {item.fromTime + ' - ' + item.tillTime}
                                                </p>
                                            </div>
                                        </div>
                                    )
                                })
                            }
                        </div>
                        <div className="row mt-5 pl-4">
                            <div className="col-5">
                                <input placeholder={'Type here'} className={'form-control pl-3 d-inline-block p-4'} style={{borderRadius:'10px'}} type="text"/>
                            </div>
                        </div>
                        <div className="row mt-5 pl-4 mb-5">
                            <div className="col-2">
                                <h4 className={'d-inline-block'}>Agents</h4>
                                <svg onClick={addAgentsToZipCode}  width="2em" height="2em" viewBox="0 0 16 16" className="bi bi-person-plus-fill d-inline-block p-1 mb-2 ml-3 bg-white" style={{borderRadius:'50%'}}
                                     fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fillRule="evenodd"
                                          d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm7.5-3a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
                                </svg>
                            </div>
                        </div>
                        {
                            agents.map((item,i)=>{
                                return(
                                    <div key={i} className="row mt-1 pl-4 ">
                                        <div className="col-1 pb-3 bg-white pt-3">
                                            <p className={'mt-2 mb-0'}>img</p>
                                        </div>
                                        <div className="col-2 pb-3 bg-white pt-3 " >
                                            <p style={{fontSize:'10px'}} className={'mb-0 text-secondary'}>agent:</p>
                                            <h6>{item.firstName + ' ' + item.lastName}</h6>
                                        </div>
                                        <div style={{borderLeft: '1px solid gray', height: '50px'}} className={'mt-3'}/>
                                        <div className="col-3  pb-3 bg-white pt-3">
                                            <p style={{fontSize:'10px'}} className={'mb-0 text-secondary'}>location:</p>
                                            <h6>{item.address}</h6>
                                        </div>
                                        <div style={{borderLeft: '1px solid gray', height: '50px'}} className={'mt-3'}/>
                                        <div className="col-2 pb-3 bg-white pt-3">
                                            <p style={{fontSize:'10px'}} className={'mb-0 text-secondary'}>online Time:</p>
                                            <h6>{item.onlineTime}</h6>
                                        </div>
                                        <div style={{borderLeft: '1px solid gray', height: '50px'}} className={'mt-3'}/>
                                        <div className="col-2 pb-3 bg-white pt-3">
                                            <p style={{fontSize:'10px'}} className={'mb-0 text-secondary'}>orders:</p>
                                            <h6>{item.orderCount}</h6>
                                        </div>
                                        <div style={{borderLeft: '1px solid gray', height: '50px'}} className={'mt-3'}/>
                                        <div className="col-1 pb-3 bg-white pt-3">
                                            <p style={{fontSize:'10px'}} className={'mb-0 text-secondary'}>opts:</p>
                                            <h6>
                                                <svg width="1.5em" height="1.5em" viewBox="0 0 16 16" style={{borderRadius:'50%'}}
                                                     onClick={()=>removeAgent(item.id)}>
                                                    className="bi bi-person-x bg-white p-1"  fill="currentColor"
                                                    xmlns="http://www.w3.org/2000/svg">
                                                    <path fillRule="evenodd"
                                                          d="M8 5a2 2 0 1 1-4 0 2 2 0 0 1 4 0zM6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm6 5c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10zm1.146-7.85a.5.5 0 0 1 .708 0L14 6.293l1.146-1.147a.5.5 0 0 1 .708.708L14.707 7l1.147 1.146a.5.5 0 0 1-.708.708L14 7.707l-1.146 1.147a.5.5 0 0 1-.708-.708L13.293 7l-1.147-1.146a.5.5 0 0 1 0-.708z"/>
                                                </svg>
                                            </h6>
                                        </div>
                                    </div>
                                )
                            })
                        }
                    </div>

                    <Modal
                        isOpen={modalActive}
                        style={customStyles}
                        contentLabel="add-country-modal"
                    >
                        <svg onClick={closeModal} width="2em" height="2em" viewBox="0 0 16 16" className="bi bi-x d-inline-block p-1 bg-light" style={{borderRadius:'50%'}} fill="currentColor"
                             xmlns="http://www.w3.org/2000/svg">
                            <path fillRule="evenodd"
                                  d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                        </svg>
                        <Formik
                            initialValues={{
                                zipCode:zipCode.name
                            }}
                            validationSchema={Yup.object().shape({

                            })}
                            onSubmit={(values, ) =>{editZipCodeFields(values) }}
                            render={({ errors, touched }) => (
                                <div>
                                    {
                                        modalType==='EDIT'?
                                            <div>
                                                <AsyncSelect
                                                    cacheOptions
                                                    loadOptions={loadOptionsState}
                                                    defaultOptions={drawerState(states,false)}
                                                    value={selectedState}
                                                    onChange={handleChangeState}
                                                />

                                                <Select
                                                    value={selectedCounty}
                                                    onChange={handleChangeCounty}
                                                    options={drawerState(counties)}
                                                />
                                                <Form>



                                                    <div className="form-group">
                                                        <Field  placeholder={"zipCode..."} name="zipCode" type="numeric" className={'form-control' + (errors.name && touched.name ? ' is-invalid' : '')} />
                                                        <ErrorMessage name="name" component="div" className="invalid-feedback" />
                                                    </div>

                                                    <div className="form-group">
                                                        <button type="submit" className="btn btn-primary mr-2">Register</button>
                                                    </div>
                                                </Form>
                                            </div>:
                                            <div>
                                                <Select
                                                    value={agentToLink}
                                                    onChange={setCurrentAgentToState}
                                                    options={drawerState(agentsToLink,true)}
                                                />
                                                <button onClick={saveAgent} className={'btn btn-success mt-2'}>
                                                    <svg width="1em" height="1em" viewBox="0 0 16 16"
                                                         className="bi bi-check2" fill="currentColor"
                                                         xmlns="http://www.w3.org/2000/svg">
                                                        <path fillRule="evenodd"
                                                              d="M13.854 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L6.5 10.293l6.646-6.647a.5.5 0 0 1 .708 0z"/>
                                                    </svg>
                                                </button>
                                            </div>
                                    }
                                </div>
                            )}
                        >

                        </Formik>

                    </Modal>
                </div>
            </AdminLayout>

            );
    }
}


const mapStateToProps = (state) =>{
    return{
        currentId:state.zipCode.id,
        agents:state.zipCodeCurrent.agents,
        workHours:state.zipCodeCurrent.workHours,
        zipCode:state.zipCodeCurrent.zipCode,
        countyName:state.zipCodeCurrent.county,
        modalActive:state.zipCodeCurrent.modalActive,
        modalType:state.zipCodeCurrent.modalType,
        customStyles:state.state.customStyles,
        selectedState:state.zipCodeCurrent.selectedState,
        selectedCounty:state.zipCodeCurrent.selectedCounty,
        states:state.zipCodeCurrent.states,
        counties:state.zipCodeCurrent.counties,
        agentsToLink:state.zipCodeCurrent.agentsToLink,
        agentToLink:state.zipCodeCurrent.agentToLink,
    }
}

const mapDispatchToProps = (dispatch) => {
    return{
        getCurrentZipCode:(id)=>dispatch(ZipCodeCurrentAction.getCurrentZipCode(id)),

        getCountiesOfState:(page,countyName,stateId,isDispatch) =>dispatch(getCountiesOfState(page,countyName,stateId,isDispatch)),
        getStates:(name,page,size,isDispatch) =>dispatch(getStates(name,page,size,isDispatch)),


        getCurrentValuesOfZip:(stateId,zipCodeId,stateName)=>dispatch(ZipCodeCurrentAction.getCurrentValuesOfZip(stateId,zipCodeId,stateName)),
        editZipCodeFields:(payload)=>dispatch(ZipCodeCurrentAction.editZipCodeFields(payload)),
        deleteAgent:(id)=>dispatch(ZipCodeCurrentAction.deleteAgent(id)),
        getAgentsOfZipCodeToLink:()=>dispatch(ZipCodeCurrentAction.getAgentsOfZipCodeToLink()),
        saveAgent:()=>dispatch(ZipCodeCurrentAction.saveAgent()),

        updateState:(payload)=>dispatch(ZipCodeCurrentAction.updateState(payload))


    }
}
export default connect(mapStateToProps,mapDispatchToProps) (ZipCodeCurrent);