import * as FeedbackType from '../types/FeedbackType'
import {createReducer} from "../../utils/StoreUtils";

const initState={
    feedbacks:[{id:'1',agent:'John Johnson',customer:'Jonathan Jonh',email:'JohnJohnson@gmail.com',phoneNumber:'998733309',amount:1000,rating:5,comment:'                                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab aliquam, architecto at corporis cupiditate esse ipsa laudantium nam nesciunt numquam omnis, perferendis quaerat, quas quia rerum soluta voluptatibus? Consectetur, temporibus! Lorem ipsum dolor sit amet, consectetur adipisicing elit. Architecto blanditiis consectetur cupiditate optio quo tenetur veritatis. Ab animi doloremque eaque illum nam, tempore. Asperiores dolorum id in officiis similique vitae.\n'},
        {id:'1',agent:'John Johnson',customer:'Jonathan Jonh',email:'JohnJohnson@gmail.com',phoneNumber:'998733309',amount:1000,rating:5,comment:'                                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab aliquam, architecto at corporis cupiditate esse ipsa laudantium nam nesciunt numquam omnis, perferendis quaerat, quas quia rerum soluta voluptatibus? Consectetur, temporibus! Lorem ipsum dolor sit amet, consectetur adipisicing elit. Architecto blanditiis consectetur cupiditate optio quo tenetur veritatis. Ab animi doloremque eaque illum nam, tempore. Asperiores dolorum id in officiis similique vitae.\n'}],
    totalElements:0,
    currentPage:1,
    feedbackType:false,
    feedbackCount:1,
    style:{backgroundColor:'white', borderRadius:'20px' , transition:'0.5s'},
    loading:false
};

const reducers={
    [FeedbackType.FEEDBACK_GET_SUCCESS](state, payload) {
        state.loading=false
        state.feedbacks=payload.payload.object
        state.totalElements=payload.payload.totalElements
    },
    [FeedbackType.FEEDBACK_GET_ERROR](state){
        state.loading=false

    },
    [FeedbackType.FEEDBACK_GET_START](state){
        state.loading=false
    },
    updateState(state, {payload}) {
        return {
            ...state,
            ...payload
        }
    },



};
export default createReducer(initState, reducers);