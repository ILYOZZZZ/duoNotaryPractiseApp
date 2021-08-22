import {api} from "./api";
import HttpClient from "../utils/HttpClient";

export function editMainService(id,data) {
    let reqMainService={
        id,
        name:data.name,
        description:data.description,
        fromTime:data.fromTime,
        tillTime:data.tillTime,
        active:data.active,
        online:data.online
    };
    return HttpClient.doPost(api.editMainService,reqMainService)
}

export function mainServiceChangeOnline(id) {
    return HttpClient.doPut(api.changeMainServiceOnline+id)
}

export function mainServiceChangeStatus(id) {
    return HttpClient.doPut(api.changeMainServiceActive+id)
}

export function deleteMainService(id) {
    return HttpClient.doDelete(api.deleteMainService+id)
}

export function addMainService(values) {
    return HttpClient.doPost(api.saveMainService,values)
}

export function getMainService() {
    return HttpClient.doGet(api.getMainService)
}