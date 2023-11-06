package tn.gymapp.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.gymapp.Entities.Exercice;
import tn.gymapp.Entities.Muscle;

@Repository
public interface ExerciceRep extends CrudRepository<Exercice, Long> {
	

	
    @Query("SELECT  DISTINCT e.label FROM Exercice e WHERE e.muscle = :muscle")
    List<String> findDistinctLabelsByMuscle(@Param("muscle") Muscle muscle);


}
