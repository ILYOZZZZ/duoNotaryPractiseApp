import {createReducer} from "../../utils/StoreUtils";
import * as authActionTypes from "../types/AuthActionTypes";
import {TOKEN} from "../../utils/constants";

const initState = {
    loading: false,
    userType:'',
    currentUser:'',
    isOpen:false,
    isAdmin:false,
    isAgent:false,
    isUser:false,
    isSuperAdmin:false,
};

const reducers = {
    [authActionTypes.AUTH_GET_USER_TOKEN_SUCCESS](state, action) {
        state.currentUser = action.payload;
        state.modalShow = false;
    },
    updateState(state, {payload}) {
        return {
            ...state,
            ...payload
        }
    },
};

export default createReducer(initState, reducers);