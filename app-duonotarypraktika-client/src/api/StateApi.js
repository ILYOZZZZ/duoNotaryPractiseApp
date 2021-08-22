import HttpClient from "../utils/HttpClient";
import {api} from './api'
import {makeUrlWithParams} from './SendApiWithParams'


export function getStates(page,size,stateName){

    let url = makeUrlWithParams(api.getStates,{page,size,stateName});
    return  HttpClient.doGet(url)
}

export function getCurrentState(id){
    let url = makeUrlWithParams(api.getCurrentState,{id})
    return HttpClient.doGet(url)
}

export function saveState(data){
    return HttpClient.doPost(api.saveState,data)
}

export function editState(id,data){
    return HttpClient.doPost(api.editState + id,data)
}

export function changeStateActive(id){
    return HttpClient.doPost(api.editStateActive+id)
}