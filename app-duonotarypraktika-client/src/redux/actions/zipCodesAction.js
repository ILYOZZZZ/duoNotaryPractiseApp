import * as ZipCodeType from '../types/ZipCodeType'



import * as ZipCodeApi from "../../api/ZipCodeApi";
import {store} from "../../App";

export function updateState(payload){
    return{
        type:ZipCodeType.ZIP_CODE_UPDATE_STATE,
        payload
    }
}

export const getZipCodes=(page,size,name)=> async (dispatch)=>{
    await dispatch({
        api: () => ZipCodeApi.getZipCodes(page,size,name),
        types:[
            ZipCodeType.REQUEST_START,
            ZipCodeType.GET_ZIP_CODES_SUCCESS,
            ZipCodeType.RESPONSE_ERROR
        ]
    })
};



export const saveZipCodes=(payload)=>async (dispatch)=>{
    const {selectedCounty} = store.getState().zipCode
    await dispatch({
        api: () => ZipCodeApi.saveZipCode({countyId:selectedCounty.value, zipCode:payload.zipCode}),
        types:[
            ZipCodeType.REQUEST_START,
            "",
            ZipCodeType.RESPONSE_ERROR
        ]
    })
}