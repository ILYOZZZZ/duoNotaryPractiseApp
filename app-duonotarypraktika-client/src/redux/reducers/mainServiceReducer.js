import * as mainServiceType from '../types/MainServiceType'
import {createReducer} from "../../utils/StoreUtils";
import {NotificationManager} from 'react-notifications';

const initState = {
    loading: false,
    mainServiceArray: []
};

const reducer = {
    [mainServiceType.REQUEST_START](state) {
        state.loading = true;
    },

    [mainServiceType.GET_MAIN_SERVICE_SUCCESS](state, payload) {
        state.loading = false;
        // console.log(payload);
        state.mainServiceArray = payload.payload.object
    },
    [mainServiceType.ADD_MAIN_SERVICE_SUCCESS](state, payload) {
        state.loading = false;
        if(payload.payload.success){
            NotificationManager.success('Successfully saved','New Main Service')
        }else{
            NotificationManager.error('Could not save')
        }
    },
    [mainServiceType.DELETE_MAIN_SERVICE_SUCCESS](state,payload){
      state.loading=false;
        console.log(payload);
      if (payload.payload.success){
          NotificationManager.success(payload.payload.message,'Delete Main Service');
      }else{
          NotificationManager.error(payload.payload.message)
      }
    },
    [mainServiceType.CHANGE_STATUS_SUCCESS](state,payload){
        state.loading=false;
        if (payload.payload.success){
            NotificationManager.success('Successfully changed','Main Service Status');
        }else{
            NotificationManager.error(payload.payload.message)
        }
    },
    [mainServiceType.CHANGE_ONLINE_SUCCESS](state,payload){
        state.loading=false;
        if (payload.payload.success){
            NotificationManager.success('Successfully changed','Main Service Online');
        }else{
            NotificationManager.error(payload.payload.message)
        }
    },
    [mainServiceType.EDIT_MAIN_SERVICE](state,payload){
        state.loading=false;
        if (payload.payload.success){
            NotificationManager.success(payload.payload.message,'Main Service Edit');
        }else{
            NotificationManager.error(payload.payload.message);
        }
    },
    [mainServiceType.RESPONSE_ERROR](state) {
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