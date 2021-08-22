import HttpClient from "../utils/HttpClient";
import {api} from './api'

export const loginUser = (data = {phoneNumberOrEmail: null, password: null}) => {
    return HttpClient.doPost(api.login, data);
}
export const me = (data = {username: null, password: null}) => {
    return HttpClient.doGet(api.userMe);
}
