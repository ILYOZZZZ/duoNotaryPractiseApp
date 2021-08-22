import HttpClient from "../utils/HttpClient";
import {api} from './api'

export function changeStatusCompanyWorkTime(id) {
    return HttpClient.doPut(api.changeCompanyWorkTimeActive+id);
}

export function deleteWorkTime(id) {
    return HttpClient.doDelete(api.deleteCompanyWorkTime+id)
}


export function editCompanyWorkTime(data) {
    return HttpClient.doPost(api.editCompanyWorkTime,data)
}


export function addCompanyWorkTime(data) {
    return HttpClient.doPost(api.addCompanyWorkTime,data)
}


export function getMainServices() {
    return HttpClient.doGet(api.getMainService)
}


export function getCompanyWorkTime() {
    return HttpClient.doGet(api.getCompanyWorkTime)
}