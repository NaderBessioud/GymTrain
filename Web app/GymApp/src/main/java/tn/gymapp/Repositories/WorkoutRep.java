package tn.gymapp.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.gymapp.Entities.Workout;

@Repository
public interface WorkoutRep extends CrudRepository<Workout, Long>{

}
