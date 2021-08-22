import React, {useEffect, useState} from 'react';
import {useDispatch, useSelector} from "react-redux";
import AdminLayout from "../../component/AdminLayout";
import * as mainServiceAction from '../../redux/actions/mainServiceAction';
import {NotificationContainer, NotificationManager} from 'react-notifications';
import {AvForm, AvInput, AvField, AvFeedback, AvGroup} from 'availity-reactstrap-validation';
import {Button, Modal, ModalHeader, ModalBody, ModalFooter} from 'reactstrap'
import 'react-notifications/lib/notifications.css';
import '../../styles/main-service-styles.scss'

const MainService = (props) => {
    const dispatch = useDispatch();
    const {loading, mainServiceArray} = useSelector(state => state.mainService);

    const [stopEffect, setStopEffect] = useState(false);
    const [showModal, setShowModal] = useState(false);
    const [showSureModal, setShowSureModal] = useState(false);

    const [changeStatusActive, setChangeStatusActive] = useState(false);
    const [changeStatusOnline, setChangeStatusOnline] = useState(false);
    const [deleteService, setDeleteService] = useState(false);

    const [message, setMessage] = useState('');
    const [title, setTitle] = useState('');
    const [id, setId] = useState(null);

    const [currentItem, setCurrentItem] = useState({});

    useEffect(() => {
        if (!stopEffect) {
            dispatch(mainServiceAction.getMainService());
            setStopEffect(true);
        }
    });

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

    function addOrEditMainService(errors, event, values) {
        if (id != null) {
            dispatch(mainServiceAction.editMainService(id, values));
            setStopEffect(false);
            dispatch(mainServiceAction.getMainService());
            setCurrentItem({});
            openCloseModal();
        } else {
            dispatch(mainServiceAction.addMainService(values));
            setStopEffect(false);
            dispatch(mainServiceAction.getMainService());
            openCloseModal();
        }
    }

    const deleteMainService = (id) => {
        dispatch(mainServiceAction.deleteMainService(id));
        setStopEffect(false);
        dispatch(mainServiceAction.getMainService());
        openSureModal();
        setId(null);
    };

    function openSureModal(id, message) {
        setShowSureModal(!showSureModal);
        switch (message) {
            case 'active':
                setTitle('Changing status');
                setMessage('Sure want to change status?');
                setId(id);
                setChangeStatusActive(true);
                setChangeStatusOnline(false);
                setDeleteService(false);
                break;
            case 'online':
                setTitle('Changing online');
                setMessage('Sure want to change online?');
                setId(id);
                setChangeStatusOnline(true);
                setChangeStatusActive(false);
                setDeleteService(false);
                break;
            case 'delete':
                setTitle('Deleting Main Service');
                setMessage('Sure want to delete service?');
                setId(id);
                setDeleteService(true);
                setChangeStatusActive(false);
                setChangeStatusOnline(false);
                break;
        }
    }

    function openCloseModal(title, item) {
        if (item != null) {
            setId(item.id);
            setCurrentItem(item);
        }else{
            setCurrentItem({});
        }
        setTitle(title);
        setShowModal(!showModal);
    }

    const changeActive = (id) => {
        dispatch(mainServiceAction.changeStatus(id));
        setStopEffect(false);
        dispatch(mainServiceAction.getMainService());
        openSureModal();
        setId(null);
    };

    const changeOnline = (id) => {
        dispatch(mainServiceAction.changeOnline(id));
        setStopEffect(false);
        dispatch(mainServiceAction.getMainService());
        openSureModal();
        setId(null);
    };

    function deleteOrChangeStatus(id) {
        if (changeStatusActive) {
            changeActive(id);
        } else if (changeStatusOnline) {
            changeOnline(id);
        } else if (deleteService) {
            deleteMainService(id);
        }
    }

    function createNotification(type, message, title) {
        switch (type) {
            case 'info':
                NotificationManager.info(message);
                break;
            case 'success':
                NotificationManager.success(message, title);
                break;
            case 'warning':
                NotificationManager.warning(message, 'Close after 3000ms', 3000);
                break;
            case 'error':
                NotificationManager.error(message, 'Click me!', 5000, () => {
                    alert('callback');
                });
                break;
        }
    }

    return (
        <div>
            <AdminLayout pathname={props.location.pathname}>
                <div className="container-fluid py-5 bg-white">

                    <div className="row mx-3">
                        <div className="col-md-1">
                            <h3>
                                Main Service
                            </h3>
                        </div>
                        <div className="col-md-1 offset-9 text-center">
                            <button className="btn main-btn" onClick={() => alert("notification")}>
                                <svg width="18" height="22" viewBox="0 0 18 22" fill="none"
                                     xmlns="http://www.w3.org/2000/svg">
                                    <path
                                        d="M15 8C15 6.4087 14.3679 4.88258 13.2426 3.75736C12.1174 2.63214 10.5913 2 9 2C7.4087 2 5.88258 2.63214 4.75736 3.75736C3.63214 4.88258 3 6.4087 3 8V16H15V8ZM17 16.667L17.4 17.2C17.4557 17.2743 17.4896 17.3626 17.498 17.4551C17.5063 17.5476 17.4887 17.6406 17.4472 17.7236C17.4057 17.8067 17.3419 17.8765 17.2629 17.9253C17.1839 17.9741 17.0929 18 17 18H1C0.907144 18 0.816123 17.9741 0.737135 17.9253C0.658147 17.8765 0.594313 17.8067 0.552787 17.7236C0.51126 17.6406 0.493682 17.5476 0.502021 17.4551C0.51036 17.3626 0.544287 17.2743 0.6 17.2L1 16.667V8C1 5.87827 1.84286 3.84344 3.34315 2.34315C4.84344 0.842855 6.87827 0 9 0C11.1217 0 13.1566 0.842855 14.6569 2.34315C16.1571 3.84344 17 5.87827 17 8V16.667ZM6.5 19H11.5C11.5 19.663 11.2366 20.2989 10.7678 20.7678C10.2989 21.2366 9.66304 21.5 9 21.5C8.33696 21.5 7.70107 21.2366 7.23223 20.7678C6.76339 20.2989 6.5 19.663 6.5 19Z"
                                        fill="#313E47"/>
                                </svg>
                            </button>
                        </div>
                        <div className="col-md-1">
                            <button className="btn main-btn" onClick={() => alert("out")}>
                                <svg width="22" height="20" viewBox="0 0 22 20" fill="none"
                                     xmlns="http://www.w3.org/2000/svg">
                                    <path
                                        d="M10 20C4.477 20 0 15.523 0 10C0 4.477 4.477 2.81829e-06 10 2.81829e-06C11.5527 -0.00116364 13.0842 0.359775 14.4729 1.05414C15.8617 1.74851 17.0693 2.75718 18 4H15.29C14.1352 2.98176 12.7112 2.31836 11.1887 2.0894C9.66625 1.86044 8.11007 2.07566 6.70689 2.70922C5.30371 3.34277 4.11315 4.36776 3.27807 5.66119C2.44299 6.95462 1.99887 8.46153 1.999 10.0011C1.99913 11.5407 2.4435 13.0475 3.27879 14.3408C4.11409 15.6341 5.30482 16.6589 6.7081 17.2922C8.11139 17.9255 9.66761 18.1405 11.19 17.9113C12.7125 17.6821 14.1364 17.0184 15.291 16H18.001C17.0702 17.243 15.8624 18.2517 14.4735 18.9461C13.0846 19.6405 11.5528 20.0013 10 20ZM17 14V11H9V9H17V6L22 10L17 14Z"
                                        fill="#313E47"/>
                                </svg>
                            </button>
                        </div>
                    </div>

                    <div className="row mt-5 mx-3">
                        <div className="col-md-4">
                            <div className="row">
                                <button className="btn add text-center"
                                        onClick={() => openCloseModal('Adding Main Service')}>
                                    <h3 className="text-white">+</h3>
                                </button>
                                <h5 className="text-black-50 mt-3 ml-3">Add New</h5>
                            </div>
                        </div>
                    </div>

                    <div className="row mt-4 px-1">
                        <table className={'table'}>
                            <thead className="thead-light">
                            <tr>
                                <th scope="col"><h5 className={'m-0'}>N</h5></th>
                                <th scope="col"><h5 className={'m-0'}>Name</h5></th>
                                <th scope="col"><h5 className={'m-0'}>From time</h5></th>
                                <th scope="col"><h5 className={'m-0'}>Till time</h5></th>
                                <th scope="col"><h5 className={'m-0'}>Description</h5></th>
                                <th scope="col"><h5 className={'m-0'}>Status</h5></th>
                                <th scope="col"><h5 className={'m-0'}>Online</h5></th>
                                <th scope="col"><h5 className={'m-0'}>Operation</h5></th>
                            </tr>
                            </thead>
                            <tbody>
                            {mainServiceArray ? mainServiceArray.map((item, index) =>
                                <tr key={index + 1}>
                                    <th scope="row">{item.showNumber}</th>
                                    <td>{item.name}</td>
                                    <td>{formatAMPM(item.fromTime)}</td>
                                    <td>{formatAMPM(item.tillTime)}</td>
                                    <td>{item.description}</td>
                                    <td><input type="checkbox" checked={item.active} id={'active'}
                                               onChange={() => openSureModal(item.id, 'active')}/><label
                                        htmlFor="active">Active</label></td>
                                    <td><input type="checkbox" checked={item.online} id={'online'}
                                               onChange={() => openSureModal(item.id, 'online')}/><label
                                        htmlFor="online">Online</label>
                                    </td>
                                    <td>
                                        <button className="btn" onClick={() => openCloseModal('Editing Main', item)}>
                                            <i className="fa fa-pencil"></i></button>
                                        <button className="btn ml-3" onClick={() => openSureModal(item.id, 'delete')}>
                                            <i className="fa fa-trash"></i></button>
                                    </td>
                                </tr>
                            ) : ''}
                            </tbody>
                        </table>
                    </div>

                    {showModal ?
                        <div className="modalMainService p-2">

                            <div className="row my-5">
                                <div className="col-md-8 offset-3">
                                    <h5>{title}</h5>
                                </div>
                            </div>

                            <AvForm onSubmit={addOrEditMainService} id={'mainServiceModal'}>
                                <AvGroup className={'p-0 m-0'}>
                                    <label htmlFor="name">Enter Name</label>
                                    <AvInput name={'name'} type={'text'} id={'name'}
                                             defaultValue={currentItem && currentItem.name} required/>
                                    <AvFeedback>fill the form!</AvFeedback>
                                </AvGroup>
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
                                    <label htmlFor="description">Enter Description</label>
                                    <AvInput name={'description'} type={'text'} id={'description'}
                                             defaultValue={currentItem && currentItem.description}/>
                                </AvGroup>
                                <AvGroup className={'p-0 m-0'}>
                                    <label htmlFor="showNumber">Choose the number by how service is shown</label>
                                    <AvInput name={'showNumber'} type={'number'} id={'showNumber'}
                                             defaultValue={currentItem && currentItem.showNumber}/>
                                </AvGroup>
                                <AvGroup className={'p-0 m-0'}>
                                    <div className="row p-2">
                                        <div className="col-md-4">
                                            <AvField name={'active'} type={'checkbox'} label={'active?'}
                                                     defaultValue={currentItem && currentItem.active}/>
                                        </div>
                                        <div className="col-md-4 offset-4 pl-5">
                                            <AvField name={'online'} type={'checkbox'} label={'online?'}
                                                     defaultValue={currentItem && currentItem.online}/>
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
                                                form={'mainServiceModal'}>
                                            submit
                                        </button>
                                    </div>
                                </div>
                            </AvForm>

                        </div> : ''}

                    <Modal isOpen={showSureModal} toggle={() => setShowSureModal(false)}>
                        <ModalHeader toggle={() => setShowSureModal(false)}>{title}</ModalHeader>
                        <ModalBody>
                            {message}
                        </ModalBody>
                        <ModalFooter>
                            <Button color="secondary" onClick={() => deleteOrChangeStatus(id)}>ok</Button>{' '}
                            <Button color="secondary" onClick={() => setShowSureModal(false)}>Cancel</Button>
                        </ModalFooter>
                    </Modal>
                    <NotificationContainer/>
                </div>
            </AdminLayout>
        </div>
    );
};

MainService.propTypes = {};

export default MainService;