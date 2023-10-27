package tn.gymapp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.gymapp.Entities.Exercice;

import tn.gymapp.Repositories.ExerciceRep;

@Service
public class ExerciceService {
	
	@Autowired
	private ExerciceRep exerciceRep;
	
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

}
