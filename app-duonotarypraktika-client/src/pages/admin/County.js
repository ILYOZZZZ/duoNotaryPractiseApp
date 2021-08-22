import React, {Component} from 'react';
import {connect} from 'react-redux'
import 'bootstrap/dist/css/bootstrap.css'
import Modal from 'react-modal';
import * as Yup from 'yup'
import {Formik, Field, Form, ErrorMessage} from 'formik';
import Pagination from 'react-js-pagination'
import AsyncSelect from 'react-select/async';


import * as CountyAction from '../../redux/actions/countyAction'


class County extends Component {

    componentDidMount(){
        this.props.getStates('',1,5,true)
    }

    componentWillMount() {
        Modal.setAppElement('body');
    }

    render() {
        const {states,counties,modalType,modal,currentPage,countyName,currentStateId,customStyles,county,totalElements} = this.props

        const drawerState = (states) =>{
            let statesToDraw = []


            states.map(item=>{
                statesToDraw.push({label:item.name + ' ' + item.description + ' ' , value:item.id})
            })

            return statesToDraw
        }

        const loadStates = (inputValue, callback) => {
            let data = this.props.getStates( inputValue, 1 , 10,true);
            data.then(value=>{
                callback(drawerState(value.content))
            })
        };

        const handleSelectBox = selected =>{
            this.props.updateState({
                currentStateId:selected.value
            })
            this.props.getCountiesOfState(1,'',selected.value,true)

            document.getElementById('search').value=''
        }

        const searchCounty = (event) =>{
            this.props.updateState({
                countyName:event.target.value
            })
            this.props.getCountiesOfState(currentPage,event.target.value,currentStateId,true)
        }

        const changeCountyActive = (id) =>{
            this.props.changeCountyActive(id)
            this.props.getCountiesOfState(currentPage,countyName,currentStateId,true)
        }

        const openModal = (type) =>{
            this.props.updateState({
                modal:true,
                modalType:type
            })
        }

        const closeModal = () =>{
            this.props.updateState({
                currentCounty: {name:'',description:''},
                modal:false,
                modalType:''
            })
        }

        const saveCounty = (values ) =>{
            if(modalType==='CREATE'){
                this.props.saveCounty(values).then(()=>{
                    this.props.getCountiesOfState(currentPage,countyName,currentStateId,true)
                })
            }else{
                this.props.changeCounty(county.id, values).then(()=>{
                    this.props.getCountiesOfState(currentPage,countyName,currentStateId,true)
                })
            }
            this.props.updateState({
                countyName:''
            })
            closeModal()
        }


        const getCurrentCounty = (id) =>{
            this.props.getCurrentCounty(id)
        }

        const pagination = (pageNumber) =>{
            // this.props.setCurrentPageToState(pageNumber)
            this.props.getCountiesOfState(pageNumber,countyName,currentStateId,true)
        }

        return (

            <div className="col-12 mt-5">
                <AsyncSelect
                    cacheOptions
                    loadOptions={loadStates}
                    defaultOptions={drawerState(states)}
                    onChange={handleSelectBox}
                />

                <input disabled={currentStateId === ''} value={countyName} type="text" id={'search'} placeholder={'search...'} onChange={searchCounty} className={'form-control mt-2 mb-2'}/>

                <button disabled={currentStateId === ''} onClick={()=>openModal('CREATE')} className={'btn btn-success'}>save New County To current State</button>

                <table className={'table'}>
                    <thead>
                    <tr>
                        <th>No#</th>
                        <th>county name</th>
                        <th>county description</th>
                        <th>county active</th>
                        <th>options</th>
                    </tr>
                    </thead>
                    <tbody>
                    {

                        counties.map((item,index)=>{
                            return(
                                <tr key={index}>
                                    <td>{index+1}</td>
                                    <td>{item.name}</td>
                                    <td>{item.description}</td>
                                    <td>{item.active?'TRUE':'FALSE'} <button className={'btn btn-outline-danger'} onClick={()=>changeCountyActive(item.id)}>Change</button></td>
                                    <td><button className={'btn btn-danger mt-4'} onClick={()=>getCurrentCounty(item.id)}>Edit</button></td>
                                </tr>
                            )
                        })}
                    </tbody>
                </table>

                <Pagination
                    prevPageText='prev'
                    nextPageText='next'
                    activePage={currentPage}
                    itemsCountPerPage={5}
                    totalItemsCount={totalElements}
                    pageRangeDisplayed={5}
                    onChange={pagination.bind(this)}
                />

                <Modal
                    isOpen={modal}
                    onRequestClose={closeModal}
                    style={customStyles}
                    contentLabel="add-country-modal"
                >
                    <button className={'btn btn-dark'} onClick={closeModal}>Close</button>
                    <Formik
                        initialValues={{
                            name:county.name,
                            description:county.description,
                            active:true,
                        }}
                        validationSchema={Yup.object().shape({
                            name:Yup.string()
                                .required('Name is Required')
                        })}
                        onSubmit={(values, ) =>{saveCounty(values) }}
                        render={({ errors, touched }) => (
                            this.props.modalType==='CREATE'?
                                <Form>
                                    <div className="form-group">
                                        <label htmlFor="name">countyName</label>
                                        <Field name="name" type="text" className={'form-control' + (errors.name && touched.name ? ' is-invalid' : '')} />
                                        <ErrorMessage name="name" component="div" className="invalid-feedback" />

                                        <label htmlFor="description">county description</label>
                                        <Field name="description" type="text" className={'form-control' + (errors.description && touched.description ? ' is-invalid' : '')} />
                                        <ErrorMessage name="description" component="div" className="invalid-feedback" />

                                        <span >county Active</span>
                                        <Field name="active" type="checkbox" className={'form-control' + (errors.active && touched.active ? ' is-invalid' : '')} />
                                        <ErrorMessage name="active" component="div" className="invalid-feedback" />

                                    </div>

                                    <div className="form-group">
                                        <button type="submit" className="btn btn-primary mr-2">Register</button>
                                        <button type="reset" className="btn btn-secondary">Reset</button>
                                    </div>
                                </Form>

                                :<Form>
                                    <div className="form-group">
                                        <label htmlFor="name">countyName</label>
                                        <Field name="name" type="text" className={'form-control' + (errors.name && touched.name ? ' is-invalid' : '')} />
                                        <ErrorMessage name="name" component="div" className="invalid-feedback" />

                                        <label htmlFor="description">county description</label>
                                        <Field name="description" type="text" className={'form-control' + (errors.description && touched.description ? ' is-invalid' : '')} />
                                        <ErrorMessage name="description" component="div" className="invalid-feedback" />
                                    </div>

                                    <div className="form-group">
                                        <button type="submit" className="btn btn-primary mr-2">Register</button>
                                    </div>
                                </Form>
                        )}
                    >

                    </Formik>
                </Modal>
            </div>

        );
    }
}

const mapStateToProps = (state) => {
    return{
        states : state.county.states,
        totalStateElements:state.county.totalStateElements,
        counties:state.county.counties,
        currentPage:state.county.currentPage,
        totalElements: state.county.totalElements,
        modalType:state.county.modalType,
        countyName:state.county.countyName,
        modal:state.county.modal,
        currentStateId: state.county.currentStateId,
        customStyles:state.county.customStyles,
        county:state.county.currentCounty,

    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        getStates:(name,page,size,isDispatch) =>dispatch(CountyAction.getStates(name,page,size,isDispatch)),
        getCountiesOfState:(page,countyName,stateId,isDispatch) =>dispatch(CountyAction.getCountiesOfState(page,countyName,stateId,isDispatch)),
        changeCountyActive:(id)=>dispatch(CountyAction.changeCountyActive(id)),
        saveCounty:(payload)=>dispatch(CountyAction.saveCounty(payload)),
        changeCounty:(id,payload)=>dispatch(CountyAction.changeCounty(id,payload)),
        getCurrentCounty:(id)=>dispatch(CountyAction.getCurrentCounty(id)),
        updateState:(payload)=>dispatch(CountyAction.updateState(payload)),
    };
};
export default connect(mapStateToProps,mapDispatchToProps)(County);