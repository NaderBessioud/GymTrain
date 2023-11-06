package tn.gymapp.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.gymapp.Entities.Bodypart;
import tn.gymapp.Entities.Exercice;
import tn.gymapp.Entities.Muscle;
import tn.gymapp.Repositories.BodyPartRep;
import tn.gymapp.Repositories.ExerciceRep;
import tn.gymapp.Repositories.MuscleRep;

@Service
public class ExerciceService {
	
	@Autowired
	private ExerciceRep exerciceRep;
	
	@Autowired
	private BodyPartRep bodyPartRep;
	
	@Autowired
	private MuscleRep muscleRep;
	
	public Exercice findByid(long id) {
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
	
	
	public List<String> LoadBodyPart(){
		List<String> result = new ArrayList<>();
		for(Bodypart bp : bodyPartRep.findAll())
			result.add(bp.getLabel());
		return result;
	}
	
	public List<String> LoadMuscleByBodyPart(String label){
		List<String> result = new ArrayList<>();
		Bodypart bodypart=bodyPartRep.findByLabel(label);
		for(Muscle m : muscleRep.findByBodypart(bodypart))
			result.add(m.getLabel());
		return result;
	}
	
	public List<String> LoadExercicesByMuscle(String label){
		
		Muscle muscle= muscleRep.findByLabel(label);
		
		return exerciceRep.findDistinctLabelsByMuscle(muscle);
	}
	
	

}
