import HttpClient from "../utils/HttpClient";
import {api} from './api'

import {makeUrlWithParams} from './SendApiWithParams'

export function getCurrentZipCode(id){
    return HttpClient.doGet(api.getCurrentZipCode +'/'+id)
}

export function getCurrentValuesOfZip(stateId,zipCodeId , stateName){
    let url = makeUrlWithParams(api.getCurrentValuesOfZipCodeToEdit,{stateId,zipCodeId,stateName});
    return HttpClient.doGet(url)
}

export function editZipCodeFields(data,id){
    return HttpClient.doPut(api.editZipCode+'/'+id,data)
}

export function deleteAgent(id){
    HttpClient.doDelete(api.removeAgent+'/'+id).then(r => console.log(r))
}

export function getAgentsOfZipCodeToLink(stateId,zipCodeId){
    let url = makeUrlWithParams(api.getAgentToLinkZip,{stateId,zipCodeId});
    return HttpClient.doGet(url)
}

export function saveAgent(data){
    return HttpClient.doPost(api.saveAgentToZip,data)
}