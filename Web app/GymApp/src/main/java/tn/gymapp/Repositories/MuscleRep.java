package tn.gymapp.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.gymapp.Entities.Bodypart;
import tn.gymapp.Entities.Muscle;

@Repository
public interface MuscleRep extends CrudRepository<Muscle, Long> {
	
	List<Muscle> findByBodypart(Bodypart bodypart);
	Muscle findByLabel(String label);

}
