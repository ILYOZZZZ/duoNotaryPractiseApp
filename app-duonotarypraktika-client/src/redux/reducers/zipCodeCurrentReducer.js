import * as ZipCodeCurrentType from '../types/ZipCodeCurrentType'
import {createReducer} from "../../utils/StoreUtils";
import {ZIP_CODE_CURRENT_GET_AGENTS_TO_ADD} from "../types/ZipCodeCurrentType";

const initState={

    agents:[{id:'1',firstName:'Cameron',lastName:'Williamson',address:'Los Angeles Street 90 zipCode:895235',onlineTime:5.10,orderCount:22},
        {id:'2',firstName:'John',lastName:'Adamson',address:'New York Street 17 zipCode:452728728',onlineTime:8.10,orderCount:15}
    ],
    workHours:[{serviceName:'online',fromTime:'10.00 AM',tillTime:'18.00 PM'},{serviceName:'in-person',fromTime:'9.00 AM',tillTime:'18.00 PM'}],
    county:{name:'',id:''},
    zipCode: {name:'',id:''},
    modalActive:false,
    modalType:'',



    selectedState: {label:'',value:''},
    selectedCounty: {label:'',value:''},
    states:[],
    counties:[],
    agentsToLink:[],
    agentToLink:'',
    loading:false

};

const reducers={


    [ZipCodeCurrentType.ZIP_CODE_CURRENT_GET_START](state){
        state.loading = true
    },
    [ZipCodeCurrentType.ZIP_CODE_CURRENT_GET_SUCCESS](state,payload){
        state.agents=payload.payload.object.agents
        state.workHours=payload.payload.object.workHours
        state.zipCode={id:payload.payload.object.id,name:payload.payload.object.zipCode}
        state.county={
            id:payload.payload.object.stateId,name:payload.payload.object.stateName
        }
        state.loading =false;
    },
    [ZipCodeCurrentType.ZIP_CODE_CURRENT_GET_ERROR](state){
        state.loading=false
    },

    [ZipCodeCurrentType.ZIP_CODE_CURRENT_GET_INPUT_VALUES_SUCCESS](state,payload){
        state.states=payload.payload.object[0].content
        state.counties=payload.payload.object[1].content
        state.selectedCounty={label:payload.payload.object[2].name,value:payload.payload.object[2].id}
        state.modalActive=true
        state.modalType="EDIT"
    },

    [ZipCodeCurrentType.ZIP_CODE_CURRENT_GET_AGENTS_TO_ADD](state,payload){
        state.agentsToLink=payload.payload.object
        state.modalType=''
        state.modalActive=true
    },


    [ZipCodeCurrentType.ZIP_CODE_CURRENT_UPDATE_STATE](state, {payload}) {
        return {
            ...state,
            ...payload
        }
    },
};
export default createReducer(initState, reducers);