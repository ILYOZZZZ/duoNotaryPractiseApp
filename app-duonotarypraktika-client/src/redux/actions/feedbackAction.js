import * as FeedbackType from '../types/FeedbackType'
import * as feedbackApi from '../../api/FeedbackApi'
export function updateState(payload){
    return{
        type:'updateState',
        payload
    }
}

export const getFeedbacks =(page,size,type)  => async (dispatch)=>{
    await dispatch({
        api: () => feedbackApi.getFeedbacks(page,size,type),
        types:[
            FeedbackType.FEEDBACK_GET_START,
            FeedbackType.FEEDBACK_GET_SUCCESS,
            FeedbackType.FEEDBACK_GET_ERROR
        ],
        data:{page,size,type}
    })
}

export const sendFeedbackAnswer =(data)  => async (dispatch)=>{
    await dispatch({
        api: () => feedbackApi.sendFeedbackAnswer(data),
        types:[
            FeedbackType.FEEDBACK_GET_START,
            "",
            FeedbackType.FEEDBACK_GET_ERROR
        ],
        data:{data}
    })
}