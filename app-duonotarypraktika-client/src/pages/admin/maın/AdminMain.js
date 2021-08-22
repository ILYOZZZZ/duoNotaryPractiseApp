import React, {useEffect, useState} from 'react';
import {useDispatch, useSelector} from "react-redux";
import AdminLayout from "../../../component/AdminLayout";
import * as adminAction from "../../../redux/actions/adminMainAction";
import Switch from 'react-switch'
import '../../../styles/main-page-style.scss'
import DayPicker from 'react-day-picker'

const AdminMain = (props) => {

    const dispatch = useDispatch();

    const {loading, ServiceArray, MainServiceArray, PublicHolidayArray, CompanyWorkTImeArray} = useSelector(state => state.admin);

    const [stopEffect, setStopEffect] = useState(false);
    const [showCalendar, setShowCalendar] = useState(false);
    const [date, setDate] = useState("00:00");

    useEffect(() => {
        if (!stopEffect) {
            dispatch(adminAction.getServices());
            dispatch(adminAction.getMainServices());
            dispatch(adminAction.getHolidays());
            dispatch(adminAction.getCompanyWorkTime());
            setStopEffect(true);
        }
    });

    const changeServiceActive = (id) => {
        dispatch(adminAction.serviceChangeActive(id));
        setStopEffect(false);
        dispatch(adminAction.getServices());
    };
    const changeMainServiceActive = (id) => {
        dispatch(adminAction.mainServiceChangeActive(id));
        setStopEffect(false);
        dispatch(adminAction.getMainServices());
    };
    const changePublicHolidayActive = (id) => {
        dispatch(adminAction.publicHolidayChangeActive(id));
        setStopEffect(false);
        dispatch(adminAction.getHolidays())
    };
    const changeCompanyWorkTimeActive = (id) => {
        dispatch(adminAction.companyWorkTimeChangeActive(id));
        setStopEffect(false);
        dispatch(adminAction.getCompanyWorkTime());
    };

    const formatAMPM = (time) => {
        if (time!=null){
            let hours = time.substring(0, 2);
                let minutes = time.substring(3, 5);
            let ampm = hours >= 12 ? 'PM' : 'AM';
            hours = hours % 12;
            hours = hours ? hours : 12;
            minutes = minutes < 10 ? minutes : minutes;
            return hours + ':' + minutes + ' ' + ampm;
        }else{
            return '00:00'
        }
    };

    const openCalendar = (date) => {
        setShowCalendar(!showCalendar);
        setDate(date);
    };

    const mainService = MainServiceArray ? MainServiceArray.map((item, index) => <div className="col-md-6"
                                                                                      key={index + 1}>
        <div className="card card1">
            <div className="card-body align-items-baseline">
                <div className="row">
                    <div className="col-md-4">
                        <h6 className="mt-2">{item.name}</h6>
                    </div>
                    <div className="col-md-4 offset-3">
                        <h6 className="mt-2">{formatAMPM(item.fromTime)} - {formatAMPM(item.tillTime)}</h6>
                    </div>
                    <div className="col-md-1 px-0 pt-1">
                        <svg width="20" height="20" viewBox="0 0 20 20" fill="none"
                             xmlns="http://www.w3.org/2000/svg">
                            <path
                                d="M15 2H19C19.2652 2 19.5196 2.10536 19.7071 2.29289C19.8946 2.48043 20 2.73478 20 3V19C20 19.2652 19.8946 19.5196 19.7071 19.7071C19.5196 19.8946 19.2652 20 19 20H1C0.734784 20 0.48043 19.8946 0.292893 19.7071C0.105357 19.5196 0 19.2652 0 19V3C0 2.73478 0.105357 2.48043 0.292893 2.29289C0.48043 2.10536 0.734784 2 1 2H5V0H7V2H13V0H15V2ZM13 4H7V6H5V4H2V8H18V4H15V6H13V4ZM18 10H2V18H18V10Z"
                                fill="#313E47"/>
                        </svg>
                    </div>
                </div>
            </div>
        </div>
    </div>) : <h1>Error</h1>;

    const companyWorkTime = CompanyWorkTImeArray ? CompanyWorkTImeArray.map((item, index) => <div className="row my-2"
                                                                                                  key={index + 1}>
        <div className="col-md-12">
            <div className="card card2">
                <div className="card-body">
                    <div className="row">
                        <div className="col-md-1">
                            <Switch onChange={() => changeCompanyWorkTimeActive(item.id)} checked={item.active}/>
                        </div>
                        <div className="col-md-4">
                            <h6 className="text-black-50 mt-1">{item.mainService.name}</h6>
                        </div>
                        <div className="col-md-1  offset-3 text-center">
                            <h6 className="text-black-50 mt-1">{item.chargePercent}%</h6>
                        </div>
                        <div className="column">
                            <svg width="1" height="34" viewBox="0 0 1 34" fill="none"
                                 xmlns="http://www.w3.org/2000/svg">
                                <line opacity="0.1" x1="0.5" y1="2.18558e-08" x2="0.499999" y2="34"
                                      stroke="black"/>
                            </svg>
                        </div>
                        <div className="col-md-2 text-center">
                            <h6 className="mt-1">{formatAMPM(item.fromTime)} - {formatAMPM(item.tillTime)}</h6>
                        </div>
                        <div className="column">
                            <button className="btn p-0">
                                <svg width="14" height="8" viewBox="0 0 14 8" fill="none"
                                     xmlns="http://www.w3.org/2000/svg">
                                    <path
                                        d="M6.99974 5.172L11.9497 0.222L13.3637 1.636L6.99974 8L0.635742 1.636L2.04974 0.222L6.99974 5.172Z"
                                        fill="#313E47"/>
                                </svg>
                            </button>
                        </div>
                        <div className="column pl-4 ml-2">
                            <button className="btn py-0 px-1 align-content-center"
                                    onClick={() => alert("Adding new Company Work Time")}>
                                <h4>+</h4>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>) : <h1>Error</h1>;

    const mainService2 = MainServiceArray ? (MainServiceArray.map((item, index) => <div className="col-md-6"
                                                                                        key={index + 1}>
        <div className="card">
            <div className="card-body p-4">
                <h3>{item.name}</h3>
                <p>{item.description}</p>
                <Switch onChange={() => changeMainServiceActive(item.id)} checked={item.active}/>
            </div>
        </div>
    </div>)) : (<h1>Error</h1>);

    const holidays = PublicHolidayArray ? PublicHolidayArray.map((item, index) => <div className="col-md-6"
                                                                                       key={index + 1}>
        <div className="card card2 my-1">
            <div className="card-body">
                <div className="row">
                    <div className="col-md-8">
                        <h6 className={'text-black-50'}>{item.name}</h6>
                    </div>
                    <div className="col-md-1 offset-1" onClick={() => openCalendar(item.date)}
                         style={{position: "relative"}}>
                        <svg width="20" height="20" viewBox="0 0 20 20" fill="none"
                             xmlns="http://www.w3.org/2000/svg">
                            <path
                                d="M15 2H19C19.2652 2 19.5196 2.10536 19.7071 2.29289C19.8946 2.48043 20 2.73478 20 3V19C20 19.2652 19.8946 19.5196 19.7071 19.7071C19.5196 19.8946 19.2652 20 19 20H1C0.734784 20 0.48043 19.8946 0.292893 19.7071C0.105357 19.5196 0 19.2652 0 19V3C0 2.73478 0.105357 2.48043 0.292893 2.29289C0.48043 2.10536 0.734784 2 1 2H5V0H7V2H13V0H15V2ZM13 4H7V6H5V4H2V8H18V4H15V6H13V4ZM18 10H2V18H18V10Z"
                                fill="#313E47"/>
                        </svg>
                    </div>

                    <div className="col-md-2">
                        <Switch onChange={() => changePublicHolidayActive(item.id)} checked={item.active}/>
                    </div>
                </div>
            </div>
        </div>
    </div>) : <h1>Error</h1>;

    const service = ServiceArray ? (ServiceArray.map((item, index) => <div className="col-md-6 my-1" key={index + 1}>
        <div className="card" key={index + 1}>
            <div className="card-body">
                <div className="row">
                    <div className="col-md-8">
                        <h6 className="mt-2">{item.name}</h6>
                    </div>
                    <div className="col-md-1 offset-1">
                        <button className="btn" onClick={() => alert("Editing Service")}>
                            <i className="fa fa-pencil icon"></i>
                        </button>
                    </div>
                    <div className="col-md-2 pt-1">
                        <Switch onChange={() => changeServiceActive(item.id)} checked={item.active}/>
                    </div>
                </div>
            </div>
        </div>
    </div>)) : (<h1>Error</h1>);

    return (
        <div className="container-fluid">
            <AdminLayout pathname={props.location.pathname}>
                <div className="pt-5 pb-5 pr-4 pl-5 bg">

                    <div className="row">
                        <div className="col-md-2">
                            <h4><b>Main page</b></h4>
                        </div>
                    </div>
                    <div className="px-2 py-1">
                        <div className="row mt-4">
                            <div className="col-md-4">
                                <h4>Work hours</h4>
                            </div>
                        </div>

                        <div className="row mt-1 mb-3">
                            {mainService}
                        </div>

                        {companyWorkTime}

                        <div className="row my-3">
                            {mainService2}
                        </div>

                        <div className="row mt-4">
                            <div className="col-md-4">
                                <h4>Public holidays</h4>
                                {showCalendar ? <div className="modalCalendar" id={"modalCalendar"}>
                                    <DayPicker
                                        disabledDays={new Date(date)}
                                        initialMonth={new Date(date)}
                                    />
                                </div> : ''
                                }

                            </div>
                        </div>

                        <div className="row my-1">
                            {holidays}
                        </div>

                        <div className="row mt-4">
                            <div className="col-md-4">
                                <h4>Services</h4>
                            </div>
                        </div>

                        <div className="row my-1">
                            {service}
                        </div>
                    </div>

                </div>
            </AdminLayout>
        </div>
    );
};

AdminMain.propTypes = {};

export default AdminMain;