import * as ZipCodeCurrentType from '../types/ZipCodeCurrentType'
import axios from "axios";



import {TOKEN} from "../../utils/constants";
import {api} from "../../api/api";
import * as ZipCurrentApi from "../../api/ZipCodeCurrentApi";
import {config} from "../../utils/config";
import {store} from "../../App";

let headers = {'Access-Control-Allow-Origin': '*'};
const token = localStorage.getItem(TOKEN);


export function updateState(payload){
    return{
        type:ZipCodeCurrentType.ZIP_CODE_CURRENT_UPDATE_STATE,
        payload
    }
}


export const getCurrentZipCode = (id) => async (dispatch)=>{
    await dispatch({
        api: () => ZipCurrentApi.getCurrentZipCode(id),
        types:[
            ZipCodeCurrentType.ZIP_CODE_CURRENT_GET_START,
            ZipCodeCurrentType.ZIP_CODE_CURRENT_GET_SUCCESS,
            ZipCodeCurrentType.ZIP_CODE_CURRENT_GET_ERROR
        ]
    })
}

export const getCurrentValuesOfZip = (stateId,zipCodeId , stateName) => async (dispatch)=>{
    await dispatch({
        api: () => ZipCurrentApi.getCurrentValuesOfZip(stateId,zipCodeId,stateName),
        types:[
            ZipCodeCurrentType.ZIP_CODE_CURRENT_GET_START,
            ZipCodeCurrentType.ZIP_CODE_CURRENT_GET_INPUT_VALUES_SUCCESS,
            ZipCodeCurrentType.ZIP_CODE_CURRENT_GET_ERROR
        ]
    })
}

export const editZipCodeFields = (payload) => async (dispatch)=>{
    const {selectedCounty , zipCode} = store.getState().zipCodeCurrent
    await dispatch({
        api: () => ZipCurrentApi.editZipCodeFields({zipCode:payload.zipCode, countyId:selectedCounty.value},zipCode.id),
        types:[
            ZipCodeCurrentType.ZIP_CODE_CURRENT_GET_START,
            "",
            ZipCodeCurrentType.ZIP_CODE_CURRENT_GET_ERROR
        ]
    })
}

export const deleteAgent = (id) => async (dispatch)=>{
    await dispatch({
        api: () => ZipCurrentApi.deleteAgent(id),
        types:[
            ZipCodeCurrentType.ZIP_CODE_CURRENT_GET_START,
            "",
            ZipCodeCurrentType.ZIP_CODE_CURRENT_GET_ERROR
        ]
    })
}

export const getAgentsOfZipCodeToLink = () => async (dispatch)=>{
    alert('a')
    const {county,zipCode} = store.getState().zipCodeCurrent
    await dispatch({
        api: () => ZipCurrentApi.getAgentsOfZipCodeToLink(county.id,zipCode.id),
        types:[
            ZipCodeCurrentType.ZIP_CODE_CURRENT_GET_START,
            ZipCodeCurrentType.ZIP_CODE_CURRENT_GET_AGENTS_TO_ADD,
            ZipCodeCurrentType.ZIP_CODE_CURRENT_GET_ERROR
        ]
    })
}

export const saveAgent =()=>async (dispatch)=>{
    const {zipCode , agentToLink} = store.getState().zipCodeCurrent
    await dispatch({
        api: () => ZipCurrentApi.saveAgent({agentId:agentToLink.value,
            zipCodeId:zipCode.id}),
        types:[
            ZipCodeCurrentType.ZIP_CODE_CURRENT_GET_START,
            " ",
            ZipCodeCurrentType.ZIP_CODE_CURRENT_GET_ERROR
        ]
    })
}