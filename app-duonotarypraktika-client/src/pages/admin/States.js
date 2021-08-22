import React, {Component} from 'react';
import {connect} from 'react-redux'


import {getStates,updateState,getCurrentState,saveState,editState,changeStateActive} from '../../redux/actions/stateAction'
import Modal from "react-modal";
import {ErrorMessage, Field, Form, Formik} from "formik";
import * as Yup from "yup";
import Pagination from "react-js-pagination";


class States extends Component {

    componentDidMount() {
        this.props.getStates(1,5,'')
    }

    render() {
        const {states,stateToEdit,totalElements,stateName,currentPage,customStyles,modal,modalType} = this.props


        const findState = (event) =>{
            this.props.getStates(currentPage,5,event.target.value)
            this.props.updateState({stateName:event.target.value})
        }

        const pagination = (pageNumber) =>{
            this.props.getStates(pageNumber,5,stateName)
            // this.props.updateState({currentPage:pageNumber})
        }

        const openModal = (type) =>{
            this.props.updateState({
                modal:true,
                modalType:type
            })
        }

        const closeModal = () =>{
            this.props.updateState({
                modal:false,
                modalType:'',
                stateToEdit:{id:'',active:'',description:'',name:''},
                stateName:''
            })
        }

        const saveOrEditState = (values) =>{
            if(modalType==='CREATE'){
                this.props.saveState(values).then(()=>{
                    this.props.getStates(1,5,'')
                })
            }else if(modalType==='EDIT'){
                this.props.editState(values).then(()=>{
                    this.props.getStates(1,5,'')
                })
            }
            closeModal()
        }

        const getCurrentState=(id)=> {
            this.props.getCurrentState(id)
        }


        const changeStateActive = (id) =>{
            this.props.changeStateActive(id)
            this.props.getStates(1,5,'')
        }

        return (
            // <AdminLayout pathname={this.props.location.pathname}>
                <div className="col-12 mt-5">
                    <button className={'btn btn-success'} onClick={()=>openModal('CREATE')} >add</button>
                    <input type="text" onChange={findState}  value={stateName} className={'form-control'} placeholder={'search...'}/>
                    <table className={'table'}>
                        <thead>
                        <tr>
                            <th className={'pr-5 pl-5'}>No</th>
                            <th className={'pr-5 pl-5'}>Name</th>
                            <th className={'pr-5 pl-5'}>Description</th>
                            <th className={'pr-5 pl-5'}>Active</th>
                            <th className={'pr-5 pl-5'}>opt</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            states.map((item,i)=>{
                                return(
                                    <tr key={i}>
                                        <td className={'pr-5 pl-5'}>{i+1}</td>
                                        <td className={'pr-5 pl-5'}>{item.name}</td>
                                        <td className={'pr-5 pl-5'}>{item.description}</td>
                                        <td className={'pr-5 pl-5'}>{item.active?'TRUE':'FALSE'} <button onClick={()=>changeStateActive(item.id)}>Edit</button></td>
                                        <td className={'pr-5 pl-5'}><button className={'btn btn-danger'} onClick={()=>getCurrentState(item.id)}>change</button></td>
                                    </tr>
                                )
                            })
                        }
                        </tbody>
                    </table>

                    <Modal
                        isOpen={modal}
                        onRequestClose={closeModal}
                        style={customStyles}
                    >
                        <button onClick={closeModal}>close</button>
                        <Formik
                            initialValues={{
                                stateName:stateToEdit!==undefined?stateToEdit.name:'',
                                description:stateToEdit!==undefined?stateToEdit.description:'',
                                active:false,
                            }}
                            validationSchema={Yup.object().shape({

                            })}
                            onSubmit={(values, ) =>{ saveOrEditState(values )}}
                            render={({ errors,  touched }) => (
                                this.props.modalType==='CREATE'?
                                    <Form>
                                        <div className="form-group">
                                            <label htmlFor="stateName">name</label>
                                            <Field name="stateName" type="text" className={'form-control' + (errors.name && touched.name ? ' is-invalid' : '')} />
                                            <ErrorMessage name="stateName" component="div" className="invalid-feedback" />

                                            <label htmlFor="description">description</label>
                                            <Field name="description" type="text" className={'form-control' + (errors.description && touched.description ? ' is-invalid' : '')} />
                                            <ErrorMessage name="description" component="div" className="invalid-feedback" />

                                            <span >Active</span>
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
                                            <label htmlFor="stateName">name</label>
                                            <Field name="stateName" type="text" className={'form-control' + (errors.name && touched.name ? ' is-invalid' : '')} />
                                            <ErrorMessage name="stateName" component="div" className="invalid-feedback" />

                                            <label htmlFor="description">description</label>
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



                    <Pagination
                        prevPageText='prev'
                        nextPageText='next'
                        activePage={currentPage}
                        itemsCountPerPage={5}
                        totalItemsCount={totalElements}
                        pageRangeDisplayed={5}
                        onChange={pagination.bind(this)}
                    />
                </div>

            // </AdminLayout>
        );
    }
}



States.propTypes = {};

const mapStateToProps = (state) =>{
    return{
        states: state.state.states,
        stateToEdit: state.state.stateToEdit,
        totalElements:state.state.totalElements,
        stateName:state.state.stateName,
        currentPage:state.state.currentPage,
        customStyles:state.state.customStyles,
        modal:state.state.modal,
        modalType:state.state.modalType,
    }
}

const mapDispatchToProps = (dispatch) => {
    return{
        getStates: (page,size,name)=>dispatch(getStates(page,size,name)),
        getCurrentState:(id)=>dispatch(getCurrentState(id)),
        saveState:(payload)=>dispatch(saveState(payload)),
        editState:(payload)=>dispatch(editState(payload)),
        changeStateActive:(id)=>dispatch(changeStateActive(id)),
        updateState:(payload)=>dispatch(updateState(payload)),
    }
}
export default connect(mapStateToProps,mapDispatchToProps)(States);