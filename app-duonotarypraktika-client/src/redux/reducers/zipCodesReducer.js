import * as ZipCodeType from '../types/ZipCodeType'
import {createReducer} from "../../utils/StoreUtils";

const initState={
    name:'',
    zipCodes:[],
    id:'',
    pageType:{value: 'ZipCode', label: 'zipCode'},
    optionsOfPage:[
        { value: 'ZipCode', label: 'zipCode' },
        { value: 'County', label: 'County' },
        { value: 'State', label: 'State' },],
    modalActive:false,
    currentCountyId:'',
    countiesToAddZip:[],
    selectedCounty:{},
    loading:false
};

const reducers={
    [ZipCodeType.REQUEST_START](state){
        state.loading = true
    },
    [ZipCodeType.GET_ZIP_CODES_SUCCESS](state,payload){
        state.zipCodes=payload.payload.object
        state.loading =false;
    },
    [ZipCodeType.RESPONSE_ERROR](state){
        state.loading=false
    },
    [ZipCodeType.ZIP_CODE_UPDATE_STATE](state, {payload}) {
        return {
            ...state,
            ...payload
        }
    },
};
export default createReducer(initState, reducers);