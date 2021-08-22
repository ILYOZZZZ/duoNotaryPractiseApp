import * as StateType from '../types/StateType'



import * as StateApi from "../../api/StateApi";
import {store} from "../../App";



export function updateState(payload){
    return{
        type:StateType.STATE_UPDATE_STATE,
        payload
    }
}


export const getStates = (page,size,name) => async (dispatch)=>{
    await dispatch({
        api: () => StateApi.getStates(page,size,name),
        types:[
            StateType.STATE_GET_START,
            StateType.STATE_GET_SUCCESS,
            StateType.STATE_GET_ERROR
        ]
    })
}

export const getCurrentState = (id) => async (dispatch)=>{
    await dispatch({
        api: () => StateApi.getCurrentState(id),
        types:[
            StateType.STATE_GET_START,
            StateType.STATE_GET_CURRENT_SUCCESS,
            StateType.STATE_GET_ERROR
        ]
    })
}

export const saveState = (payload) => async (dispatch)=>{
    await dispatch({
        api: () => StateApi.saveState({name:payload.stateName, description:payload.description, active:payload.active}),
        types:[
            StateType.STATE_GET_START,
            "",
            StateType.STATE_GET_ERROR
        ]
    })
}


export const editState = (payload) => async (dispatch)=>{
    const {stateToEdit} = store.getState().state
    await dispatch({
        api: () => StateApi.editState("/" + stateToEdit.id,{name:payload.stateName, description:payload.description}),
        types:[
            StateType.STATE_GET_START,
            "",
            StateType.STATE_GET_ERROR
        ]
    })
}

export const changeStateActive = (id) => async (dispatch)=>{
    await dispatch({
        api: () => StateApi.changeStateActive("/" + id),
        types:[
            StateType.STATE_GET_START,
            "",
            StateType.STATE_GET_ERROR
        ]
    })
}
