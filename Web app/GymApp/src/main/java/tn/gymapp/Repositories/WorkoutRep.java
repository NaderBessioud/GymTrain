package tn.gymapp.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.gymapp.Entities.TypeWorkout;
import tn.gymapp.Entities.User;
import tn.gymapp.Entities.Weighthistory;
import tn.gymapp.Entities.Workout;

@Repository
public interface WorkoutRep extends CrudRepository<Workout, Long>{
	List<Workout> findByUserwOrderByDateDesc(User user);

}
