import {createReducer} from "../../utils/StoreUtils";
import * as adminType from "../types/AdminMainType";

const initState = {
    loading: false,
    ServiceArray: [],
    MainServiceArray: [],
    PublicHolidayArray: [],
    CompanyWorkTImeArray: []
};

const reducers = {
    [adminType.REQUEST_START](state) {
        state.loading = true
    },
    [adminType.GET_SERVICES_SUCCESS](state, payload) {
        state.loading = false;
        state.ServiceArray = payload.payload.object
    },
    [adminType.GET_MAIN_SERVICES_SUCCESS](state, payload) {
        state.loading = false;
        state.MainServiceArray = payload.payload.object;
    },
    [adminType.GET_PUBLIC_HOLIDAYS_SUCCESS](state, payload) {
        state.loading = false;
        state.PublicHolidayArray = payload.payload.object;
    },
    [adminType.GET_COMPANY_WORK_TIME_SUCCESS](state, payload) {
        state.loading = false;
        state.CompanyWorkTImeArray = payload.payload.object;
    },
    [adminType.RESPONSE_ERROR](state) {
        state.loading = false
    },
    updateState(state, {payload}) {
        return {
            ...state,
            ...payload
        }
    },
};

export default createReducer(initState, reducers);