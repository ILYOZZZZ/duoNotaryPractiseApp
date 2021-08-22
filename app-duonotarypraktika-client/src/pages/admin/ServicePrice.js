import React, {Component} from 'react';
import axios from 'axios';
import {Col, Modal, ModalBody, ModalFooter, ModalHeader, Table} from "reactstrap";
import {AvForm, AvField, AvInput} from 'availity-reactstrap-validation'
import {Token} from "../../redux/Token";
import Label from "reactstrap/es/Label";
import Input from "reactstrap/es/Input";
import AdminLayout from "../../component/AdminLayout";

class ServicePrice extends Component {

    constructor(props) {
        super(props);
        this.state = {
            price: [],
            openAndCloseModal: false,
            modalName: '',
            currentItem: '',
            currentItemId: '',
            zipCodes: [],
            service: [],
            isEdit: false,
            deleteModal: false,
            currentDeleteId: '',
        }
    }

    componentDidMount() {
        this.getPrice();
        this.getZipCode();
        this.getService();
    }

    getPrice = () => {
        axios({
            url: 'http://localhost/api/servicePrice/getPrice',
            method: 'get'
        }).then(res => {
            this.setState({
                price: res.data.object
            })
        })
    };

    getZipCode = () => {
        axios({
            url: 'http://localhost/api/zipcode',
            method: 'get',
            Header: {
                Authorization: 'Bearer' + Token
            },
            params: {
                page: 1,
                size: 5
            }
        }).then(res => {
            this.setState({
                zipCodes: res.data.content
            })
        })
    };

    getService = () => {
        axios({
            url: 'http://localhost/api/Services',
            method: 'get'
        }).then(res => {
            this.setState({
                service: res.data.object
            })
        })
    };

    changeActiveTwo = (id) => {
        // console.log(id)
        axios({
            url: 'http://localhost/api/servicePrice/changeActive/' + id,
            method: 'put',
        }).then(this.getPrice)
    };

    addPrice = (event, err, values) => {
        // console.log(values, "VALUES");
        if (this.state.modalName === "Edit") {
            axios({
                url: 'http://localhost/api/servicePrice/savePrice',
                method: 'post',
                data: {
                    id: this.state.currentItemId,
                    price: values.price,
                    chargeMinute: values.minute,
                    chargePercent: values.percent,
                    serviceId: values.service,
                    active: values.active
                }
            }).then(res => {
                this.getPrice();
                this.closeModal();
                this.setState({
                    currentElement: []
                })
            })
        } else if (this.state.modalName === "Save") {
            axios({
                url: 'http://localhost/api/servicePrice/savePrice',
                method: 'post',
                data: {
                    price: values.price,
                    chargeMinute: values.minute,
                    chargePercent: values.percent,
                    zipCodeIds: [values.zipCode],
                    serviceId: values.service,
                    active: values.active
                }
            }).then(res => {
                this.getPrice();
                this.closeModal();
            })
        }
    };
    openModal = (name, item) => {
        if (name === "Save") {
            this.setState({
                openAndCloseModal: true,
                modalName: name,
                currentItem: [],
                isEdit: false
            })
        } else {
            this.setState({
                openAndCloseModal: true,
                modalName: name,
                currentItemId: item.id,
                currentItem: item,
                isEdit: true
            })
        }
    };
    closeModal = () => {
        this.setState({
            openAndCloseModal: false,
            currentItemId: '',
            modalName: ''
        })
    };
    deletePrice = (id) => {
        axios({
            url: 'http://localhost/api/servicePrice/' + id,
            method: 'delete'
        }).then(res => {
            this.getPrice();
            this.setState({
                deleteModal: false
            })
        })
    };
    openOrCloseDeleteModal = (id) => {
        this.setState({
            deleteModal: !this.state.deleteModal,
            currentDeleteId: id,
        })
    };

    render() {
        const {price, openAndCloseModal, modalName, currentItem, zipCodes, service, isEdit, deleteModal, currentDeleteId} = this.state;


        return <div className={"Container"}>
            <AdminLayout pathname={this.props.location.pathname}>
                <Table className={"table border"}>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Price</th>
                        <th>Charge Minute</th>
                        <th>Charge Percent</th>
                        <th>Zip Codes</th>
                        <th>Service</th>
                        <th>Active</th>
                        <th>Options</th>
                        <th>
                            <button className={'btn btn-secondary'} style={{width: '40px'}}
                                    onClick={() => this.openModal("Save")}>+
                            </button>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        price.map((item, index) => (
                            <tr key={index}>
                                <td>{index + 1}</td>
                                <td>{item.price}</td>
                                <td>{item.chargeMinute}</td>
                                <td>{item.chargePercent}</td>
                                <td>{item.zipCode.zipCode}</td>
                                <td>{item.service.name}</td>
                                <td><input type={'checkbox'} onChange={() => this.changeActiveTwo(item.id)}
                                           checked={item.active}/>
                                </td>
                                <td className={'d-flex'}>
                                    <button className={"btn btn-danger"}
                                            onClick={() => this.openOrCloseDeleteModal(item.id)}> x
                                    </button>
                                </td>
                                <td>
                                    <button className={"btn btn-outline-info"}
                                            onClick={() => this.openModal("Edit", item)}>edit
                                    </button>
                                </td>
                            </tr>
                        ))
                    }
                    </tbody>
                </Table>
                <Modal isOpen={openAndCloseModal}>
                    <h3 className={'text-center mt-2 mb-0'}>{modalName}</h3>
                    <AvForm onSubmit={this.addPrice}>
                        <ModalBody>
                            <AvField defaultValue={currentItem && currentItem.price} type={'number'} label={'Enter Price'}
                                     min={0}
                                     name={'price'} required/>
                            <AvField defaultValue={currentItem && currentItem.chargeMinute} type={'number'} min={0}
                                     label={'Enter ChargeMinute'} name={'minute'} required/>
                            <AvField defaultValue={currentItem && currentItem.chargePercent} type={'number'} min={0}
                                     label={'Enter ChargePercent'} name={'percent'} required/>
                            <AvField defaultValue={currentItem && currentItem.active} name={'active'} type={'checkbox'}
                                     label={'is Price Active'}/>
                            <div>
                                {
                                    !isEdit ?
                                        <div>
                                            <AvField type={'select'} name={'zipCode'} label={'ZipCode'}>
                                                <option>Choose ZipCode</option>
                                                {
                                                    zipCodes.map(item => (
                                                        <option key={item.id} value={item.id}>{item.zipCode}</option>
                                                    ))
                                                }
                                            </AvField>
                                        </div> : ''
                                }
                            </div>
                            <AvField defaultValue={currentItem ? currentItem.service ? currentItem.service.id : '' : ''}
                                     type={'select'} name={'service'}
                                     label={'Services'}>
                                <option>Choose Service</option>
                                {
                                    service.map(item => (
                                        <option key={item.id} value={item.id}>{item.name}</option>
                                    ))
                                }
                            </AvField>
                        </ModalBody>
                        <ModalFooter className={'border-0'}>
                            <Col sm={3}>
                                <button type={'submit'} className={'btn btn-success'}>save</button>
                            </Col>
                            <Col sm={3}>
                                <button type={'close'} className={'btn btn-danger'} onClick={this.closeModal}>close</button>
                            </Col>
                        </ModalFooter>
                    </AvForm>

                </Modal>
                <Modal isOpen={deleteModal}>
                    <ModalBody>
                        you really agree to delete?
                    </ModalBody>
                    <ModalFooter>
                        <Col sm={3}>
                            <button type="submit" className="btn  btn-outline-danger"
                                    onClick={() => this.openOrCloseDeleteModal('')}>No
                            </button>
                        </Col>
                        <Col sm={3}>
                            <button type="submit" className="btn  btn-outline-success"
                                    onClick={() => this.deletePrice(currentDeleteId)}>Yes
                            </button>
                        </Col>
                    </ModalFooter>
                </Modal>
            </AdminLayout>

        </div>;
    }
}

export default ServicePrice;