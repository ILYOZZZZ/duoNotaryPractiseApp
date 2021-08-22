import HttpClient from "../utils/HttpClient";
import {api} from './api'

import {makeUrlWithParams} from './SendApiWithParams'






export function getFeedbacks(page,size,type){
    let url = makeUrlWithParams(api.getFeedbacks,{page,size,type});
    return HttpClient.doGet(url)
}

export  function sendFeedbackAnswer(data){
    return HttpClient.doPost(api.sendFeedbackAnswer,data)
}