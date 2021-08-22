import HttpClient from "../utils/HttpClient";
import {api} from './api'

export function companyWorkTimeChangeActive(id) {
    return HttpClient.doPut(api.changeCompanyWorkTimeActive+id)
}


export function getCompanyWorkTime() {
    return HttpClient.doGet(api.getCompanyWorkTime);
}


export function getMainServices() {
    return HttpClient.doGet(api.getMainService);
}

export function getServices() {
    return HttpClient.doGet(api.getServices );
}

export function getHolidays() {
    return HttpClient.doGet(api.getPublicHolidays)
}

export function serviceChangeActive(id) {
    return HttpClient.doPut(api.changeServiceActive + id)
}

export function mainServiceChangeActive(id) {
    return HttpClient.doPut(api.changeMainServiceActive + id)
}

export function publicHolidayChangeActive(id) {
    return HttpClient.doPut(api.changePublicHolidayActive + id)
}



