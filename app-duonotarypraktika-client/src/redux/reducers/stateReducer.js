import * as StateType from '../types/StateType'
import {createReducer} from "../../utils/StoreUtils";

const initState={
    states:[
    ],
    stateToEdit:{id:'',active:'',description:'',name:''},
    totalElements:0,
    stateName:'',
    currentPage:1,
    customStyles:{
        content : {
            top                   : '0%',
            left                  : '67%',
            right                 : '10%',
            marginRight           : '-10%',
            border                : '0'
        }
    },
    modal:false,
    modalType:'',
    loading:false
};

const reducers={

    [StateType.STATE_GET_START](state){
        state.loading = true
    },
    [StateType.STATE_GET_SUCCESS](state,payload){
        state.states=payload.payload.object.content
        state.totalElements=payload.payload.object.totalElements
        state.loading =false;
    },
    [StateType.STATE_GET_ERROR](state){
        state.loading=false
    },

    [StateType.STATE_GET_CURRENT_SUCCESS](state,payload){
        state.stateToEdit=payload.payload.object
        state.modal=true
        state.modalType="EDIT"
    },

    [StateType.STATE_UPDATE_STATE](state, {payload}) {
        return {
            ...state,
            ...payload
        }
    },
};
export default createReducer(initState, reducers);