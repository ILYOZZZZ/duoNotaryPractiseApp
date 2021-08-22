package uz.pdp.appduonotarypraktikaserver.service.team2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.State;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqState;
import uz.pdp.appduonotarypraktikaserver.repository.StateRepository;
import uz.pdp.appduonotarypraktikaserver.utils.CommonUtils;

import java.util.UUID;

@Service
public class    StateService {

    @Autowired
    StateRepository stateRepository;


    // get all state

   public ApiResponse getAllState(Integer page, Integer size , String name){
       Pageable pageable = CommonUtils.createPageable(page, size);
       return new ApiResponse("succesfully got",true,stateRepository.getAllStates(name,pageable));
   }


   // get one state

    public ApiResponse getStateById(UUID id){
        return new ApiResponse("succesfully got by id",true,stateRepository.getStateById(id));
    }


    // save state

    public ApiResponse saveState(ReqState reqState){

        try {
            State state = new State();
            state.setName(reqState.getName());
            state.setDescription(reqState.getDescription());
            state.setActive(reqState.isActive());
            stateRepository.save(state);
            return new ApiResponse("Successfully saved",true);
        } catch (Exception e) {
            return new ApiResponse("Didn't saved", false);
        }
    }


    // delete state

    public ApiResponse deleteState(UUID id){
        try {
            stateRepository.deleteById( id);
            return new ApiResponse("Successfully deleted",true);
        } catch (Exception e) {
            return new ApiResponse("Didn't deleted", false);
        }
    }

    // edit state

    public ApiResponse editState(ReqState reqState,UUID id){
        try {
            State state = stateRepository.findById(id).orElseThrow(() -> new ResourceNotFound("State","id", id));
            state.setName(reqState.getName());
            state.setDescription(reqState.getDescription());
            state.setActive(reqState.isActive());
            stateRepository.save(state);
            return new ApiResponse("Successfully edited",true);
        } catch (ResourceNotFound resourceNotFound) {
            return new ApiResponse("Didn't edited",false);
        }
    }

    public ApiResponse editStateActive(UUID id){
        State state = stateRepository.findById(id).orElseThrow(() -> new ResourceNotFound("State", "id", id));
        state.setActive(!state.getActive());
        stateRepository.save(state);
        return new ApiResponse("Successfully edited",true);
    }

}
