import React, {Component} from 'react';
// import PropTypes from 'prop-types';
import AdminLayout from "../../component/AdminLayout";
import {connect} from 'react-redux'
import Modal from 'react-modal';
import { Formik, Field, Form, ErrorMessage } from 'formik';
import Select from 'react-select';

import States from './States'
import County from './County'
import * as ZipCodeAction from '../../redux/actions/zipCodesAction'
import {getCountiesOfState} from '../../redux/actions/countyAction'
import * as Yup from "yup";


class ZipCodes extends Component {

    componentDidMount() {
        this.props.getAllZipCodes(1,5,'')
    }

    componentWillMount() {
        Modal.setAppElement('body');
    }

    render() {

        const {zipCodes  , pageType, optionsOfPage,modalActive,customStyles,selectedCounty , countiesToAddZip} = this.props

        const findZipCodesByCounty=(e)=>{
            this.props.getAllZipCodes(1,5,e.target.value)
        }

        const saveZipCode=(values)=>{
            this.props.saveZipCode(values).then(()=>{
                this.props.getAllZipCodes(1,5,'')
            })
            closeModal()
        }

        const getCurrentZipCode = (id)=>{
            this.props.updateState({
                id:id
            })
            this.props.history.push("/admin/zip/current")
        }

        const handleChange = selectedOpts =>{
            this.props.updateState({
                pageType:selectedOpts
            })
        }

        const openModal=(id)=>{
            this.props.getCountiesOfState(1,'',id,false).then((data)=>{
                let arrOfCountiesToDraw = []
                data.payload.object.content.map(item=>{
                    arrOfCountiesToDraw.push({label:item.name , value:item.id})
                })
                this.props.updateState({
                    countiesToAddZip: arrOfCountiesToDraw,
                    modalActive:true
                })

            })
        }

        const handleChangeOfCounty = selectedOpt =>{
            this.props.updateState({
                selectedCounty:selectedOpt
            })
        }

        const closeModal=()=>{
            this.props.updateState({
                modalActive:false
            })
        }

        const tester = (mainText , text) =>{
            let newTxt = ""

            //assalom
            //salom

            for (let i = 0; i < mainText.length; i++) {
                let counter = 0
                for (let j = 0; j < text.length; j++) {
                    if(mainText.charAt(i) === text.charAt(j)){
                        counter++
                    }
                    if(j===text.length-1){
                        if(counter===1){
                            newTxt += mainText.charAt(i)
                        }else{
                            newTxt += mainText.charAt(i) + "(" + counter + ")"
                        }
                        counter=0
                    }
                }
            }
            console.log(newTxt)

        }


        return (
            <div>
                <button onClick={()=>tester("assalom","salom")}>OK</button>
                <AdminLayout pathname={this.props.location.pathname}>
                    <div>
                        <div className="row ">
                            <div className="col-3 offset-2 mt-1">
                                <Select
                                    value={pageType}
                                    onChange={handleChange}
                                    options={optionsOfPage}
                                />
                            </div>
                            {
                                pageType.value==='ZipCode'?
                                    <div className="col-12 mt-5 ">
                                    <div className="row">
                                        <div className="col-3">
                                            <h2>ZIP codes</h2>
                                        </div>
                                        <div className="col-3 offset-3 ">
                                            <input style={{borderRadius:'20px'}} onChange={findZipCodesByCounty} placeholder={'Search'} type="text" className={'form-control pl-4'}/>
                                        </div>
                                    </div>
                                    <div className="row">
                                        {
                                            zipCodes.map((item,i)=>{
                                                return(
                                                    <div key={i} className={'col-12 mt-5'}>
                                                        <h4 className={'d-inline-block'}>{item.countyName}</h4>
                                                        <svg onClick={()=>openModal(item.countyId)} width="1.5em" height="1.5em" viewBox="0 0 16 16"
                                                             className="bi bi-plus-circle pb-1" fill="currentColor"
                                                             xmlns="http://www.w3.org/2000/svg">
                                                            <path fillRule="evenodd"
                                                                  d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                                            <path fillRule="evenodd"
                                                                  d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                                                        </svg>
                                                        <div  className={'row'}>
                                                            {
                                                                item.zipCodes.map((item,i)=>{
                                                                    if(i===0 || i%5===0){
                                                                        return(
                                                                                <span key={i} className={'bg-white ml-1 col-2 mb-2  p-2 pl-3 '} onClick={()=>getCurrentZipCode(item.id)} style={{borderRadius:'10px'}}>
                                                                    {item.zipCode}
                                                                                    <span className={'ml-5   align-top'}>...</span>
                                                                            </span>
                                                                        )
                                                                    }else{
                                                                        return(
                                                                            <span key={i} className={'bg-white ml-5 col-2 mb-2  p-2 pl-3 '} onClick={()=>getCurrentZipCode(item.id)} style={{borderRadius:'10px'}}>
                                                                {item.zipCode}
                                                                                <span className={'ml-5   align-top'}>...</span>
                                                            </span>
                                                                        )
                                                                    }
                                                                })
                                                            }
                                                        </div>
                                                    </div>
                                                )
                                            })
                                        }
                                    </div>
                                </div>:pageType.value==='County'?
                                    <County/>:<States/>
                            }
                        </div>


                        <Modal
                            isOpen={modalActive}
                            onRequestClose={closeModal}
                            style={customStyles}
                        >
                            <button onClick={closeModal}>close</button>

                            <Select
                                value={selectedCounty}
                                onChange={handleChangeOfCounty}
                                options={countiesToAddZip}
                            />

                            <Formik
                                initialValues={{
                                    zipCode:''
                                }}
                                validationSchema={Yup.object().shape({

                                })}
                                onSubmit={(values, ) =>{ saveZipCode(values)}}
                                render={({ errors, touched }) => (
                                    <Form>
                                        <div className="form-group">
                                            <Field name="zipCode"  type="text" className={'form-control' + (errors.name && touched.name ? ' is-invalid' : '')} />
                                            <ErrorMessage name="zipCode" component="div" className="invalid-feedback" />
                                        </div>

                                        <div className="form-group">
                                            <button type="submit" className="btn btn-primary mr-2">save</button>
                                            <button type="reset" className="btn btn-secondary">Reset</button>
                                        </div>
                                    </Form>
                                )}
                            >

                            </Formik>
                        </Modal>
                    </div>

                </AdminLayout>
            </div>
        );
    }
}

ZipCodes.propTypes = {};
const mapStateToProps = (state) =>{
    return{
        name:state.zipCode.name,
        zipCodes: state.zipCode.zipCodes,
        optionsOfPage:state.zipCode.optionsOfPage,
        pageType:state.zipCode.pageType,
        modalActive:state.zipCode.modalActive,
        selectedCounty:state.zipCode.selectedCounty,
        countiesToAddZip:state.zipCode.countiesToAddZip,
        customStyles:state.state.customStyles,
    }
}

const mapDispatchToProps = (dispatch) => {
    return{
        getAllZipCodes: (page,size,name)=>dispatch(ZipCodeAction.getZipCodes(page,size,name)),
        saveZipCode: (payload)=>dispatch(ZipCodeAction.saveZipCodes(payload)),
        updateState: (payload)=>dispatch(ZipCodeAction.updateState(payload)),
        getCountiesOfState: (page,countyName,stateId,isDispatch)=>dispatch(getCountiesOfState(page,countyName,stateId,isDispatch)),
    }
}
export default connect(mapStateToProps,mapDispatchToProps)(ZipCodes);