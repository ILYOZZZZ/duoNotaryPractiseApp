package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appduonotarypraktikaserver.entity.State;
import uz.pdp.appduonotarypraktikaserver.resModels.ResState;

import java.util.UUID;

public interface StateRepository extends JpaRepository<State, UUID> {

    @Query(value = "select cast(s.id as varchar) as id, name as name , description as description , active as active  from state s where lower(s.name) LIKE (concat('%',lower(:stateName),'%')) order by s.name", nativeQuery = true)
    Page<ResState> getAllStates(@Param(value = "stateName") String stateName, Pageable pageable);

    @Query(value = "select cast(id as varchar) as id, name as name , description as description , active as active from state where state.id=:stateId",nativeQuery = true)
    ResState getStateById(@Param(value = "stateId")UUID stateId);

    @Query(value = "select cast(s.id as varchar) as id, s.name as name , s.description as description , s.active as active from county join state s on county.state_id = s.id where county.id=:countyId ",nativeQuery = true)
    ResState getStateByCountyId(@Param(value = "countyId")UUID countyId);

}
