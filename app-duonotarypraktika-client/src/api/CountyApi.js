import HttpClient from "../utils/HttpClient";
import {api} from './api'
import {makeUrlWithParams} from './SendApiWithParams'



export function getStates(stateName,page,size){
    let url = makeUrlWithParams(api.getStates,{stateName,page,size});
    return HttpClient.doGet(url)
}

export function getCountiesOfState(page,size,countyName,stateId){
    let url = makeUrlWithParams(api.getCountiesOfState,{page,size,countyName,stateId});
    return HttpClient.doGet(url)

}

export function changeCountyActive(id){
    return HttpClient.doPost(api.editCountyActive +  id)
}

export function saveCounty(data){
    return HttpClient.doPost(api.saveCounty,data)
}

export function editCounty(id,data){
    return HttpClient.doPost(api.editCounty + '/' + id , data)
}

export function getCurrentCounty(id){
    let url = makeUrlWithParams(api.getCurrentCounty, {id});
    return HttpClient.doGet(url)
}