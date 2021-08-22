import * as adminType from '../types/AdminMainType'
import * as adminApi from '../../api/AdminApi'


export const getServices = (payload) => async (dispatch) => {
    await dispatch({
        api: adminApi.getServices,
        types: [
            adminType.REQUEST_START,
            adminType.GET_SERVICES_SUCCESS,
            adminType.RESPONSE_ERROR
        ],
        data: payload
    })
};

export const getMainServices = (payload) => async (dispatch) => {
    await dispatch({
        api: adminApi.getMainServices,
        types: [
            adminType.REQUEST_START,
            adminType.GET_MAIN_SERVICES_SUCCESS,
            adminType.RESPONSE_ERROR
        ],
        data: payload
    })
};

export const getHolidays = (payload) => async (dispatch) => {
    await dispatch({
        api: adminApi.getHolidays,
        types: [
            adminType.REQUEST_START,
            adminType.GET_PUBLIC_HOLIDAYS_SUCCESS,
            adminType.RESPONSE_ERROR
        ],
        data: payload
    })
};

export const getCompanyWorkTime = (payload) => async (dispatch) => {
    dispatch({
        api: adminApi.getCompanyWorkTime,
        types: [
            adminType.REQUEST_START,
            adminType.GET_COMPANY_WORK_TIME_SUCCESS,
            adminType.RESPONSE_ERROR
        ],
        data: payload
    })
};

export const serviceChangeActive = (id) => (dispatch) => {
    dispatch({
        api: () => adminApi.serviceChangeActive(id),
        types: [
            adminType.REQUEST_START,
            "",
            adminType.RESPONSE_ERROR
        ]
    })

};

export const mainServiceChangeActive = (id) => (dispatch) => {
    dispatch({
        api: () => adminApi.mainServiceChangeActive(id),
        types: [
            adminType.REQUEST_START,
            "",
            adminType.RESPONSE_ERROR
        ],
    })
};

export const publicHolidayChangeActive = (id) => (dispatch) => {
    dispatch({
        api: () => adminApi.publicHolidayChangeActive(id),
        types: [
            adminType.REQUEST_START,
            "",
            adminType.RESPONSE_ERROR
        ]
    })
};

export const companyWorkTimeChangeActive = (id) => (dispatch) => {
    dispatch({
        api: () => adminApi.companyWorkTimeChangeActive(id),
        types: [
            adminType.REQUEST_START,
            "",
            adminType.RESPONSE_ERROR
        ],
    })
};
