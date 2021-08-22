import * as CountyType from '../types/CountyType'

import {store} from "../../App";
import * as CountyApi from "../../api/CountyApi";

export function updateState(payload){
    return{
        type:CountyType.COUNTY_UPDATE_STATE,
        payload
    }
}



export const getCountiesOfState= (page,countyName,stateId,isDispatch) =>async(dispatch)=>{
    if(isDispatch){
        await dispatch({
            api: () => CountyApi.getCountiesOfState(page,5,countyName,stateId),
            types:[
                CountyType.COUNTY_GET_START,
                CountyType.COUNTY_GET_OF_STATE,
                CountyType.COUNTY_GET_ERROR
            ]
        })
    }else{
        return await dispatch({
            api: () => CountyApi.getCountiesOfState(page,5,countyName,stateId),
            types:[
                CountyType.COUNTY_GET_START,
                "",
                CountyType.COUNTY_GET_ERROR
            ]
        })
    }
}


// export function getCountiesOfState(page,countyName,stateId,isDispatch){
//     if (token) {
//         headers = {
//             ...headers,
//             Authorization: token
//         };
//     }
//     return (dispatch) =>{
//         return axios({
//             method: 'get',
//             url: config.BASE_URL + api.getCountiesOfState,
//             params: {
//                 page: page,
//                 size: 5,
//                 countyName: countyName,
//                 stateId:stateId
//             },
//             headers: headers
//         }).then(function (response) {
//             if (response.status !== 200) {
//                 throw Error(response.statusText);
//             }
//             else if(isDispatch){
//                 dispatch(updateState({counties: response.data.object.content,
//                     totalElements:response.data.object.totalElements}))
//             }else{
//                 return response.data.object
//             }
//         })
//     }
//
//
// }

export const getStates= (name,page,size,isDispatch) =>async(dispatch)=>{
    if(isDispatch){
        await dispatch({
            api: () => CountyApi.getStates(name,page,size),
            types:[
                CountyType.COUNTY_GET_START,
                CountyType.COUNTY_GET_STATES,
                CountyType.COUNTY_GET_ERROR
            ]
        })
    }else{
        return await dispatch({
            api: () => CountyApi.getStates(name,page,size),
            types:[
                CountyType.COUNTY_GET_START,
                "",
                CountyType.COUNTY_GET_ERROR
            ]
        })
    }
}

// export function getStates(name,page,size,isDispatch){
//     if (token) {
//         headers = {
//             ...headers,
//             Authorization: token
//         };
//     }
//      return (dispatch) =>{
//         return  axios({
//             method: 'get',
//             url: config.BASE_URL + api.getStates,
//             params: {
//                 page: page,
//                 size: size,
//                 stateName: name
//             },
//             headers: headers
//         }).then(function (response) {
//             if (response.status !== 200) {
//                 throw Error(response.statusText);
//             }
//
//             if(isDispatch){
//                 dispatch(updateState({states:response.data.object.content}))
//                 return response.data.object
//             }else{
//                 return response.data.object
//             }
//         })
//     }
// }

export const changeCountyActive= (id) => async (dispatch)=>{
    await dispatch({
        api: () => CountyApi.changeCountyActive("/" + id),
        types:[
            CountyType.COUNTY_GET_START,
            "",
            CountyType.COUNTY_GET_ERROR
        ]
    })
}

export const saveCounty= (payload) => async (dispatch)=>{
    const {currentStateId} = store.getState().county
    await dispatch({
        api: () => CountyApi.saveCounty({name:payload.name,
            description:payload.description,
            active:payload.active,
            stateId: currentStateId}),
        types:[
            CountyType.COUNTY_GET_START,
            "",
            CountyType.COUNTY_GET_ERROR
        ]
    })
}

export const changeCounty= (id,payload) => async (dispatch)=>{
    const {currentStateId} = store.getState().county
    await dispatch({
        api: () => CountyApi.editCounty(id,{name:payload.name,
            description:payload.description,
            stateId: currentStateId}),
        types:[
            CountyType.COUNTY_GET_START,
            "",
            CountyType.COUNTY_GET_ERROR
        ]
    })
}


export const getCurrentCounty= (id) => async (dispatch)=>{
    await dispatch({
        api: () => CountyApi.getCurrentCounty(id),
        types:[
            CountyType.COUNTY_GET_START,
            CountyType.COUNTY_GET_CURRENT_SUCCESS,
            CountyType.COUNTY_GET_ERROR
        ]
    })
}
