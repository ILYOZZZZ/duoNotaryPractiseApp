import * as type from '../types/MainServiceType'
import * as mainServiceApi from '../../api/MainServiceApi'

export const editMainService=(id,data,payload)=>(dispatch)=> {
    dispatch({
        api:()=>mainServiceApi.editMainService(id,data),
        types:[
            type.REQUEST_START,
            type.EDIT_MAIN_SERVICE,
            type.RESPONSE_ERROR
        ],
        data:payload
    })
};

export const getMainService = (payload) => async (dispatch) => {
    dispatch({
        api: mainServiceApi.getMainService,
        types: [
            type.REQUEST_START,
            type.GET_MAIN_SERVICE_SUCCESS,
            type.RESPONSE_ERROR
        ],
        data: payload
    })
};

export const addMainService = (values, payload) =>async (dispatch) => {
   await dispatch({
        api: () => mainServiceApi.addMainService(values),
        types: [
            type.REQUEST_START,
            type.ADD_MAIN_SERVICE_SUCCESS,
            type.RESPONSE_ERROR
        ],
        data: payload
    })
};

export const changeStatus = (id, payload) => (dispatch) => {
    dispatch({
        api: () => mainServiceApi.mainServiceChangeStatus(id),
        types: [
            type.REQUEST_START,
            type.CHANGE_STATUS_SUCCESS,
            type.RESPONSE_ERROR
        ],
        data: payload
    })
};

export const changeOnline = (id, payload) => (dispatch) => {
    dispatch({
        api: () => mainServiceApi.mainServiceChangeOnline(id),
        types: [
            type.REQUEST_START,
            type.CHANGE_ONLINE_SUCCESS,
            type.RESPONSE_ERROR
        ],
        data: payload
    })
};

export const deleteMainService = (id, payload) => (dispatch) => {
    dispatch({
        api: () => mainServiceApi.deleteMainService(id),
        types: [
            type.REQUEST_START,
            type.DELETE_MAIN_SERVICE_SUCCESS,
            type.RESPONSE_ERROR
        ],
        data: payload
    })
};