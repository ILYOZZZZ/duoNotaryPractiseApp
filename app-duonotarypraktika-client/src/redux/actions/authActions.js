import * as types from "../types/AuthActionTypes";
import {TOKEN} from "../../utils/constants";
import {me,loginUser} from "../../api/AuthApi";
import {toast} from "react-toastify";
// import * as app2 from "../../api/AppApi";
import * as app from "../../api/AuthApi";
import jwt from 'jwt-decode'


export const login = (payload) => async (dispatch, getState) => {
    await dispatch({
        api: loginUser,
        types: [
            types.REQUEST_AUTH_START,
            '',
            types.REQUEST_API_ERROR
        ],
        data: payload.v
    }).then(res => {
        if (res.success) {
            localStorage.setItem(TOKEN, res.payload.body.tokenType + ' ' + res.payload.body.accessToken);
            let parsedToken = jwt(res.payload.body.accessToken);
            console.log(parsedToken,"ParsedToken")
            setTimeout(() => {
                if (parsedToken.roles[0].name==='ROLE_SUPER_ADMIN'||parsedToken.roles[0].name==='ROLE_ADMIN'){
                    dispatch({type: 'updateState', payload:{isSuperAdmin:true}})
                    payload.history.push('/admin/adminMain')
                }
                if (parsedToken.roles[0].name==='ROLE_AGENT'){
                    dispatch({type: 'updateState', payload:{isAgent:true}})
                    payload.history.push('/agent/agentMain')
                }
                if (parsedToken.roles[0].name==='ROLE_USER'){
                    dispatch({type: 'updateState', payload:{isUser:true}})
                    payload.history.push('/client/clientMain')
                }
            }, 10);
        }
    }).catch(err => {
        // console.log(err)
        // // if (!err)
        // alert(err.response.data.message)
    })
}


export const userMe = () => async (dispatch, getState) => {
    const {auth: {currentUser}} = getState();
    if (currentUser || !localStorage.getItem(TOKEN)) {
        return;
    }
    try {
        const response = await dispatch({
            api: me,
            types: [
                types.AUTH_GET_CURRENT_USER_REQUEST,
                types.AUTH_GET_USER_TOKEN_SUCCESS,
                types.AUTH_GET_CURRENT_USER_ERROR
            ]
        });
        if (response.success) {
            dispatch({
                type: types.AUTH_GET_USER_TOKEN_SUCCESS,
                payload: response.payload
            })
            // setStateRole(response.payload.roles, dispatch);
            if (response.payload.roles[0].name==='ROLE_SUPER_ADMIN'||response.payload.roles[0].name==='ROLE_ADMIN'){
                dispatch({type: 'updateState', payload:{isSuperAdmin:true}})
                // payload.history.push('/admin/adminMain')
            }
            if (response.payload.roles[0].name==='ROLE_AGENT'){
                dispatch({type: 'updateState', payload:{isAgent:true}})
                // payload.history.push('/agent/agentMain')
            }
            if (response.payload.roles[0].name==='ROLE_USER'){
                dispatch({type: 'updateState', payload:{isUser:true}})
                // payload.history.push('/client/clientMain')
            }
        } else {
            dispatch({
                type: types.AUTH_LOGOUT
            });
        }
    } catch (e) {
        dispatch({
            type: types.AUTH_LOGOUT
        });
    }
}



