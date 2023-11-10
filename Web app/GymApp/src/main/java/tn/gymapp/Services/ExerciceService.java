package tn.gymapp.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.gymapp.Entities.Exercice;
import tn.gymapp.Entities.User;
import tn.gymapp.Entities.Workout;
import tn.gymapp.Repositories.ExerciceRep;
import tn.gymapp.Repositories.UserRep;


@Service
public class ExerciceService {
	
	@Autowired
	private ExerciceRep exerciceRep;
	
	@Autowired
	private UserRep userRep;
	
	public Exercice getExerciceById(long id) {
		return exerciceRep.findById(id).get();
	}


	public Exercice AddExercice(Exercice e) {
		
		return exerciceRep.save(e);
	}

	
	public Exercice UpdateExercice(Exercice e) {
		
		return exerciceRep.save(e);
	}

	
	public void DeleteExercice(Long id) {
		Exercice w= exerciceRep.findById(id).orElse(null);
		exerciceRep.delete(w);		
	}

	
	public List<Exercice> DisplayExercices() {
		
		return (List<Exercice>) exerciceRep.findAll();
	}
	
	
	public List<Exercice> getAllExericesByUser(long id){
		List<Exercice> result = new ArrayList<Exercice>();
		User user = userRep.findById(id).get();
		for(Workout w : user.getWorkouts()) {
			for(Exercice ex : w.getExercices()) {
				OptionalInt indexOptional = IntStream.range(0, result.size())
					    .filter(i -> result.get(i).getLabel().equals(ex.getLabel()))
					    .findFirst();
				if (indexOptional.isPresent()) {
				    int index = indexOptional.getAsInt();
				    if(result.get(index).getWorkout().getDate().before(ex.getWorkout().getDate())) {
				    	result.set(index, ex);
				    }
				} else {
				    result.add(ex);
				}
			}
		}
		
		return result;
	}
	
	
	
	public List<Exercice> getAllExericesByUserAndMuscle(long id,String label){
		
		List<Exercice> result = new ArrayList<Exercice>();
		User user = userRep.findById(id).get();
		for(Workout w : user.getWorkouts()) {
			for(Exercice ex : w.getExercices()) {
				if(ex.getMuscle().equals(label)) {
					OptionalInt indexOptional = IntStream.range(0, result.size())
						    .filter(i -> result.get(i).getLabel().equals(ex.getLabel()))
						    .findFirst();
					    
				if (indexOptional.isPresent()) {
					
				    int index = indexOptional.getAsInt();
				    if(result.get(index).getWorkout().getDate().before(ex.getWorkout().getDate())) {
				    	result.set(index, ex);
				    }
				} else {
				    result.add(ex);
				}
			}
		}
		}
		
		return result;
	}
	
	

}
