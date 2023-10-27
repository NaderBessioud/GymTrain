package tn.gymapp.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.gymapp.Entities.Muscle;

@Repository
public interface MuscleRep extends CrudRepository<Muscle, Long> {

}
