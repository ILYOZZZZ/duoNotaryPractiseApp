import * as type from '../types/CompanyWorkTimeType';
import {createReducer} from '../../utils/StoreUtils'
import {NotificationManager} from 'react-notifications';

const initState = {
    loading: false,
    companyWorkTime: [],
    mainService: [],
};

const reducer = {

    [type.REQUEST_START](state) {
        state.loading = true;
    },

    [type.GET_COMPANY_WORK_TIME](state, payload) {
        state.loading = false;
        state.companyWorkTime = payload.payload.object;
    },
    [type.GET_MAIN_SERVICE](state, payload) {
        state.loading = false;
        state.mainService = payload.payload.object;
    },
    [type.ADD_COMPANY_WORK_TIME](state, payload) {
        state.loading = false;
        if (payload.payload.success) {
            NotificationManager.success(payload.payload.message, 'New Company Work Time')
        } else {
            NotificationManager.error(payload.payload.message);
        }
    },
    [type.EDIT_COMPANY_WORK_TIME](state, payload) {
        state.loading = false;
        if (payload.payload.success) {
            NotificationManager.success(payload.payload.message, 'Company Work Time Edit')
        } else {
            NotificationManager.error(payload.payload.message);
        }
    },
    [type.CHANGE_ACTIVE_COMPANY_WORK_TIME](state,payload){
        state.loading = false;
        if (payload.payload.success) {
            NotificationManager.success(payload.payload.message, 'Change Status')
        } else {
            NotificationManager.error(payload.payload.message);
        }
    },
    [type.DELETE_COMPANY_WORK_TIME](state,payload){
        state.loading = false;
        console.log(payload);
        if (payload.payload.success) {
            NotificationManager.success(payload.payload.message, 'Delete Company Work Time')
        } else {
            NotificationManager.error(payload.payload.message);
        }
    },
    [type.RESPONSE_ERROR](state) {
        state.loading = false;
    },

    updateState(state, {payload}) {
        return {
            ...state,
            ...payload
        }
    }
};

export default createReducer(initState, reducer)
