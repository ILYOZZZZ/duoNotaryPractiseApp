import HttpClient from "../utils/HttpClient";
import {api} from './api'

import {makeUrlWithParams} from './SendApiWithParams'

export function getZipCodes(page,size,name){
    let url = makeUrlWithParams(api.getAllZipCodes,{page,size,name});
    return HttpClient.doGet(url)
}

export function saveZipCode(data){
    return HttpClient.doPost(api.saveZipCode,data)
}