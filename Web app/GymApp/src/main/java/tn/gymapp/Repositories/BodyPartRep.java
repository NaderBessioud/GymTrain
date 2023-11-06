package tn.gymapp.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.gymapp.Entities.Bodypart;

@Repository
public interface BodyPartRep extends CrudRepository<Bodypart, Long> {
	
	Bodypart findByLabel(String label);

}
