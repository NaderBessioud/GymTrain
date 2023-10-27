package tn.gymapp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.gymapp.Entities.Workout;
import tn.gymapp.Repositories.WorkoutRep;

@Service
public class WorkoutService {
	
	@Autowired
	private WorkoutRep workrep;

	public Workout findByid(long id) {
		return workrep.findById(id).get();
	}


	public Workout AddWorkout(Workout w) {
		
		return workrep.save(w);
	}

	
	public Workout UpdateWorkout(Workout w) {
		
		return workrep.save(w);
	}

	
	public void DeleteWorkout(Long id) {
		Workout w= workrep.findById(id).orElse(null);
		workrep.delete(w);		
	}

	
	public List<Workout> DisplayWorkouts() {
		
		return (List<Workout>) workrep.findAll();
	}
}
