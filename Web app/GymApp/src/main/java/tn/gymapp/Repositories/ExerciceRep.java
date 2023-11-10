package tn.gymapp.Repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.gymapp.Entities.Exercice;


@Repository
public interface ExerciceRep extends CrudRepository<Exercice, Long> {
	

	
  


}
