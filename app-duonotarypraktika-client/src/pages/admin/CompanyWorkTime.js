import React, {useState, useEffect} from 'react';
import {useDispatch, useSelector} from "react-redux";
import AdminLayout from "../../component/AdminLayout";
import * as action from "../../redux/actions/companyWorkTimeAction";
import {NotificationContainer, NotificationManager} from 'react-notifications';
import 'react-notifications/lib/notifications.css';
import '../../styles/company-work-time-style.scss';
import {AvForm, AvInput, AvField, AvFeedback, AvGroup} from 'availity-reactstrap-validation';
import {Button, Modal, ModalHeader, ModalBody, ModalFooter} from 'reactstrap';
import {useField} from "formik";


const CompanyWorkTime = (props) => {

    const dispatch = useDispatch();
    const {companyWorkTime, mainService} = useSelector(state => state.companyWorkTime);


    const [id, setId] = useState(null);
    const [mainServiceId, setMainServiceId] = useState(null);
    const [currentItem, setCurrentItem] = useState({});
    const [title, setTitle] = useState('');
    const [message, setMessage] = useState('');

    const [stopEffect, setStopEffect] = useState(false);
    const [mainModal, setMainModal] = useState(false);
    const [sureModal, setSureModal] = useState(false);

    const [changeStatus, setChangeStatus] = useState(false);
    const [deleteWorkTime, setDeleteWorkTime] = useState(false);

    useEffect(() => {
        if (!stopEffect) {
            dispatch(action.getCompanyWorkTime());
            setStopEffect(true);
        }
    });

    const openCloseModal = (title, item) => {
        if (title !== '') {
            setTitle(title);
            dispatch(action.getMainServices());
            if (item != null) {
                setCurrentItem(item);
                setId(item.id);
                setMainServiceId(item.mainService.id);
            } else {
                setId(null);
                setMainServiceId(null);
                setCurrentItem({});
            }
        }
        setMainModal(!mainModal);
    };

    const addOrEditWorkTime = (errors, event, values) => {
        if (id != null) {
            dispatch(action.editWorkTime(id, values));
            setId(null);
            setMainServiceId(null);
            setCurrentItem({});
        } else {
            dispatch(action.addWorkTime(values));
        }
        setStopEffect(false);
        dispatch(action.getCompanyWorkTime());
        openCloseModal();
        setTitle('');
    };

    const openSureModal = (id, message) => {
        setSureModal(!sureModal);
        switch (message) {
            case 'status':
                setTitle('Changing status');
                setMessage('Sure want to change status?');
                setId(id);
                setChangeStatus(true);
                setDeleteWorkTime(false);
                break;
            case 'delete':
                setTitle('Deleting Main Service');
                setMessage('Sure want to delete service?');
                setId(id);
                setDeleteWorkTime(true);
                setChangeStatus(false);
        }
    };

    const changeActive = (id) => {
        dispatch(action.changeStatus(id));
        setStopEffect(false);
        dispatch(action.getCompanyWorkTime());
        openSureModal();
    };

    const deleteCompanyWorkTime=(id)=>{
        dispatch(action.deleteWorkTime(id));
        setStopEffect(false);
        dispatch(action.getCompanyWorkTime());
        openSureModal();
    };

    const deleteOrChangeStatus = (id) => {
        if (changeStatus) {
            changeActive(id);
            setChangeStatus(false);
        } else if (deleteWorkTime) {
            deleteCompanyWorkTime(id);
            setDeleteWorkTime(false);
        }
        setId(null);
        setTitle('');
        setMessage('');
    };

    const formatAMPM = (time) => {
        if (time != null) {
            let hours = time.substring(0, 2);
            let minutes = time.substring(3, 5);
            let ampm = hours >= 12 ? 'PM' : 'AM';
            hours = hours % 12;
            hours = hours ? hours : 12;
            minutes = minutes < 10 ? minutes : minutes;
            return hours + ':' + minutes + ' ' + ampm;
        } else {
            return '00:00'
        }
    };

    return (
        <div>
            <AdminLayout pathname={props.location.pathname}>
                <div className="container-fluid bg-light p-5">

                    <div className="row">
                        <div className="col-md-2">
                            <h3>Work Time</h3>
                        </div>
                        <div className="col-md-1 offset-8 text-center">
                            <button className="btn main-btn">
                                <svg width="18" height="22" viewBox="0 0 18 22" fill="none"
                                     xmlns="http://www.w3.org/2000/svg">
                                    <path
                                        d="M15 8C15 6.4087 14.3679 4.88258 13.2426 3.75736C12.1174 2.63214 10.5913 2 9 2C7.4087 2 5.88258 2.63214 4.75736 3.75736C3.63214 4.88258 3 6.4087 3 8V16H15V8ZM17 16.667L17.4 17.2C17.4557 17.2743 17.4896 17.3626 17.498 17.4551C17.5063 17.5476 17.4887 17.6406 17.4472 17.7236C17.4057 17.8067 17.3419 17.8765 17.2629 17.9253C17.1839 17.9741 17.0929 18 17 18H1C0.907144 18 0.816123 17.9741 0.737135 17.9253C0.658147 17.8765 0.594313 17.8067 0.552787 17.7236C0.51126 17.6406 0.493682 17.5476 0.502021 17.4551C0.51036 17.3626 0.544287 17.2743 0.6 17.2L1 16.667V8C1 5.87827 1.84286 3.84344 3.34315 2.34315C4.84344 0.842855 6.87827 0 9 0C11.1217 0 13.1566 0.842855 14.6569 2.34315C16.1571 3.84344 17 5.87827 17 8V16.667ZM6.5 19H11.5C11.5 19.663 11.2366 20.2989 10.7678 20.7678C10.2989 21.2366 9.66304 21.5 9 21.5C8.33696 21.5 7.70107 21.2366 7.23223 20.7678C6.76339 20.2989 6.5 19.663 6.5 19Z"
                                        fill="#313E47"/>
                                </svg>
                            </button>
                        </div>
                        <div className="col-md-1">
                            <button className="btn main-btn">
                                <svg width="22" height="20" viewBox="0 0 22 20" fill="none"
                                     xmlns="http://www.w3.org/2000/svg">
                                    <path
                                        d="M10 20C4.477 20 0 15.523 0 10C0 4.477 4.477 2.81829e-06 10 2.81829e-06C11.5527 -0.00116364 13.0842 0.359775 14.4729 1.05414C15.8617 1.74851 17.0693 2.75718 18 4H15.29C14.1352 2.98176 12.7112 2.31836 11.1887 2.0894C9.66625 1.86044 8.11007 2.07566 6.70689 2.70922C5.30371 3.34277 4.11315 4.36776 3.27807 5.66119C2.44299 6.95462 1.99887 8.46153 1.999 10.0011C1.99913 11.5407 2.4435 13.0475 3.27879 14.3408C4.11409 15.6341 5.30482 16.6589 6.7081 17.2922C8.11139 17.9255 9.66761 18.1405 11.19 17.9113C12.7125 17.6821 14.1364 17.0184 15.291 16H18.001C17.0702 17.243 15.8624 18.2517 14.4735 18.9461C13.0846 19.6405 11.5528 20.0013 10 20ZM17 14V11H9V9H17V6L22 10L17 14Z"
                                        fill="#313E47"/>
                                </svg>
                            </button>
                        </div>
                    </div>

                    <div className="row mt-5">
                        <button className="btn btn-secondary" onClick={() => openCloseModal('New Company Work Time')}>
                            Add Work Time
                        </button>
                    </div>

                    <div className="row mt-4">
                        <div className="card workTimeCard">
                            <div className="card-body p-3">
                                <div className="row">
                                    <div className="cols">
                                        <h6 className="text-black-50 mt-2">Main service</h6>
                                    </div>
                                    <div className="cl"></div>
                                    <div className="cols">
                                        <h6 className="text-black-50 mt-2">Charge percent</h6>
                                    </div>
                                    <div className="cl"></div>
                                    <div className="cols">
                                        <h6 className="text-black-50 mt-2">From time</h6>
                                    </div>
                                    <div className="cl"></div>
                                    <div className="cols">
                                        <h6 className="text-black-50 mt-2">Till time</h6>
                                    </div>
                                    <div className="cl"></div>
                                    <div className="cols">
                                        <h6 className="text-black-50 mt-2">Status</h6>
                                    </div>
                                    <div className="cl"></div>
                                    <div className="cols">
                                        <h6 className="text-black-50 mt-2">Actions</h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    {companyWorkTime ? companyWorkTime.map((item, index) => <div className="row mt-4" key={index + 1}>
                        <div className="card workTimeCard">
                            <div className="card-body p-3">
                                <div className="row">
                                    <div className="cols">
                                        <h6 className="mt-2">{item.mainService.name}</h6>
                                    </div>
                                    <div className="cl"></div>
                                    <div className="cols">
                                        <h6 className="mt-2">{item.chargePercent}%</h6>
                                    </div>
                                    <div className="cl"></div>
                                    <div className="cols">
                                        <h6 className="mt-2">{formatAMPM(item.fromTime)}</h6>
                                    </div>
                                    <div className="cl"></div>
                                    <div className="cols">
                                        <h6 className="mt-2">{formatAMPM(item.tillTime)}</h6>
                                    </div>
                                    <div className="cl"></div>
                                    <div className="cols">
                                        <input type="checkbox" checked={item.active} className="mt-3"
                                               onChange={() =>openSureModal(item.id,'status')}/>
                                    </div>
                                    <div className="cl"></div>
                                    <div className="cols">
                                        <button className="btn mt-1"
                                                onClick={() => openCloseModal('Editing Company Work Time', item)}><i className="fa fa-pencil"></i></button>
                                        <button className="btn mt-1" onClick={()=>openSureModal(item.id,'delete')}><i className="fa fa-trash"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>) : ''}

                    {mainModal ? <div className="mainModal p-2">
                        <div className="row my-5">
                            <div className="col-md-8 offset-2 text-center">
                                <h5>{title}</h5>
                            </div>
                        </div>
                        <AvForm id={'workTimeModal'} onSubmit={addOrEditWorkTime}>
                            <AvField id={'mainService'} name={'mainServiceId'} type={'select'} label={'Main Service'}
                                     defaultValue={mainServiceId && mainServiceId} required>
                                <option>Select Main Service</option>
                                {mainService.map((item, index) =>
                                    <option value={item.id} key={index + 1}>{item.name}</option>
                                )}
                            </AvField>
                            <AvGroup className={'p-0 m-0'}>
                                <div className="row p-3">
                                    <div className="col-md-5 p-0 ">
                                        <label htmlFor="fromTime">From Time</label>
                                        <AvInput name={'fromTime'} type={'time'} id={'fromTime'}
                                                 defaultValue={currentItem && currentItem.fromTime} required/>
                                        <AvFeedback>fill the form!</AvFeedback>
                                    </div>
                                    <div className="col-md-5 p-0 offset-2">
                                        <label htmlFor="tillTime" className={'float-right'}>Till Time</label>
                                        <AvInput name={'tillTime'} type={'time'} id={'tillTime'}
                                                 defaultValue={currentItem && currentItem.tillTime} required/>
                                        <AvFeedback>fill the form!</AvFeedback>
                                    </div>
                                </div>
                            </AvGroup>
                            <AvGroup className={'p-0 m-0'}>
                                <label htmlFor="chargePercent">Charge percent</label>
                                <AvInput name={'chargePercent'} type={'number'} id={'chargePercent'}
                                         defaultValue={currentItem && currentItem.chargePercent}/>
                            </AvGroup>
                            <AvGroup className={'p-0 m-0'}>
                                <div className="row p-2">
                                    <div className="col-md-4">
                                        <AvField name={'active'} type={'checkbox'} label={'status'}
                                                 defaultValue={currentItem && currentItem.active}/>
                                    </div>
                                </div>
                            </AvGroup>
                            <div className="row mt-4">
                                <div className="col-md-4 pl-4">
                                    <button className="btn btn-dark" style={{width: '10rem'}}
                                            onClick={openCloseModal}>
                                        cancel
                                    </button>
                                </div>
                                <div className="col-md-4 pl-4 offset-2">
                                    <button className="btn btn-dark" style={{width: '10rem'}}
                                            form={'workTimeModal'}>
                                        submit
                                    </button>
                                </div>
                            </div>
                        </AvForm>
                    </div> : ''}

                    <Modal isOpen={sureModal}>
                        <ModalHeader toggle={() => setSureModal(false)}>
                            {title}
                        </ModalHeader>
                        <ModalBody>
                            {message}
                        </ModalBody>
                        <ModalFooter>
                            <Button color="secondary" onClick={() => deleteOrChangeStatus(id)}>ok</Button>{' '}
                            <Button color="secondary" onClick={() => setSureModal(false)}>Cancel</Button>
                        </ModalFooter>
                    </Modal>

                    <NotificationContainer/>
                </div>
            </AdminLayout>
        </div>
    );
};

export default CompanyWorkTime;