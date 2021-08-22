import * as type from '../types/CompanyWorkTimeType'
import * as workTimeApi from '../../api/CompanyWorkTimeApi'
import {REQUEST_AUTH_START} from "../types/AuthActionTypes";

export const getCompanyWorkTime = () => async (dispatch) => {
    await dispatch({
        api: workTimeApi.getCompanyWorkTime,
        types: [
            type.REQUEST_START,
            type.GET_COMPANY_WORK_TIME,
            type.RESPONSE_ERROR
        ]
    })
};

export const getMainServices = () => async (dispatch) => {
    await dispatch({
        api: workTimeApi.getMainServices,
        types: [
            type.REQUEST_START,
            type.GET_MAIN_SERVICE,
            type.RESPONSE_ERROR
        ]
    })
};

export const addWorkTime = (data) => (dispatch) => {
    dispatch({
        api: workTimeApi.addCompanyWorkTime,
        types: [
            type.REQUEST_START,
            type.ADD_COMPANY_WORK_TIME,
            type.RESPONSE_ERROR
        ],
        data: data
    })
};

export const editWorkTime = (id, values) => async (dispatch) => {
    let reqWorkTime = {
        id: id,
        fromTime: values.fromTime,
        tillTime: values.tillTime,
        chargePercent: values.chargePercent,
        active: values.active,
        mainServiceId: values.mainServiceId
    };
    await dispatch({
        api: workTimeApi.editCompanyWorkTime,
        types: [
            type.REQUEST_START,
            type.EDIT_COMPANY_WORK_TIME,
            type.RESPONSE_ERROR
        ],
        data: reqWorkTime
    })
};

export const deleteWorkTime=(id)=>(dispatch)=>{
    dispatch({
        api:workTimeApi.deleteWorkTime,
        types:[
            type.REQUEST_START,
            type.DELETE_COMPANY_WORK_TIME,
            type.RESPONSE_ERROR
        ],
        data:id
    })
};

export const changeStatus = (id) => (dispatch) => {
    dispatch({
        api: workTimeApi.changeStatusCompanyWorkTime,
        types: [
            type.REQUEST_START,
            type.CHANGE_ACTIVE_COMPANY_WORK_TIME,
            type.RESPONSE_ERROR
        ],
        data: id
    })
};