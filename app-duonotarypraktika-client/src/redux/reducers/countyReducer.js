import * as CountyType from '../types/CountyType'
import {createReducer} from "../../utils/StoreUtils";

const initState={
    states:[],
    currentStateId:'',
    totalStateElements:'',
    counties:[],
    currentElements:'',
    currentPage:1,
    countyName:'',
    modal:false,
    modalType:'',
    customStyles:{
        content : {
            top                   : '0%',
            left                  : '67%',
            right                 : '10%',
            marginRight           : '-10%',
        }
    },
    currentCounty: {name:'',description:''},
    totalElements:0,
    loading:false
};

const reducers={

    [CountyType.COUNTY_GET_START](state){
        state.loading = true
    },
    [CountyType.COUNTY_GET_SUCCESS](state,payload){
        alert('hello')
        console.log(payload.payload)
        state.loading =false;
    },
    [CountyType.COUNTY_GET_STATES](state,payload){
        state.states=payload.payload.object.content
        state.totalStateElements=payload.payload.object.totalElements
    },
    [CountyType.COUNTY_GET_OF_STATE](state,payload){
        state.counties=payload.payload.object.content
        state.totalElements=payload.payload.object.totalElements
    },
    [CountyType.COUNTY_GET_ERROR](state){
        state.loading=false
    },
    [CountyType.COUNTY_GET_CURRENT_SUCCESS](state,payload){
        state.currentCounty=payload.payload.object
        state.modal=true
        state.modalType='EDIT'
        state.loading=false
    },
    [CountyType.COUNTY_UPDATE_STATE](state, {payload}) {
        return {
            ...state,
            ...payload
        }
    },
};
export default createReducer(initState, reducers);