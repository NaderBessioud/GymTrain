package tn.gymapp.Services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import tn.gymapp.Entities.Exercice;
import tn.gymapp.Entities.TypeWorkout;
import tn.gymapp.Entities.User;
import tn.gymapp.Entities.Workout;
import tn.gymapp.Repositories.ExerciceRep;
import tn.gymapp.Repositories.UserRep;
import tn.gymapp.Repositories.WorkoutRep;
import tn.gymapp.dto.Workoutevent;


@Service
public class WorkoutService {
	
	@Autowired
	private WorkoutRep workrep;
	
	@Autowired
	private UserRep userRep;
	
	@Autowired
	private ExerciceRep exerciceRep;
	
	

	public Workout findByid(long id) {
		return workrep.findById(id).get();
	}


	@Transactional
	public Workout AddWorkout(Workout w,long id) {
		List<Exercice> wexs=new ArrayList<>();
		User user=userRep.findById(id).get();
		if(user.getWorkouts() == null) {
			user.setWorkouts(new HashSet<>());
		}
		w.setUserw(user);
		Workout workout= workrep.save(w);
		user.getWorkouts().add(w);
		userRep.save(user);
	for (Exercice ex:w.getExercices()) {
		ex.setImage("IronWave_"+ex.getLabel());
		ex.setWorkout(workout);
		exerciceRep.save(ex);
		
		wexs.add(ex);
	}
		
	workout.setExercices(new HashSet<>());
	workout.getExercices().addAll(wexs);
	  workrep.save(workout);
		  return workout;
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

	public List<Workoutevent> getWorkoutsDate(long id) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		User user= userRep.findById(id).get();
		List<Workoutevent> workoutsEvent=new  ArrayList<>();
		if(workrep.findByUserwOrderByDateDesc(user).size()>0 && user.getWorkoutroutine() != null) {
		Workout lastWorkout=workrep.findByUserwOrderByDateDesc(user).get(0);
		
		Calendar c = Calendar.getInstance(); 
		int currentMonth = c.get(Calendar.MONTH) + 1;
		c.setTime(lastWorkout.getDate());
		
		for (int j=0;j<6;j++) {
			int daysInMonth=0;
	        switch (currentMonth+j) {
	            case 2:
	                daysInMonth = 28; 
	                break;
	            case 4:
	            case 6:
	            case 9:
	            case 11:
	                daysInMonth = 30;
	                break;
	            default:
	                daysInMonth = 31;
	        }
	        if(lastWorkout.getType().equals(TypeWorkout.Push)) {
	        	int nb = daysInMonth / (3+Integer.parseInt(user.getWorkoutroutine().split("-")[0])+Integer.parseInt(user.getWorkoutroutine().split("-")[1]+Integer.parseInt(user.getWorkoutroutine().split("-")[2])));
	    		
	    		for(int i=1;i<=nb;i++) {
	    			c.add(Calendar.DATE, 1+Integer.parseInt(user.getWorkoutroutine().split("-")[0]));
	    			workoutsEvent.add(new Workoutevent("Pull "+i, dateFormat.format(c.getTime()),"#FFFFFF", "#FFFF00"));
	    			c.add(Calendar.DATE, 1+Integer.parseInt(user.getWorkoutroutine().split("-")[1]));
	    			workoutsEvent.add(new Workoutevent("Legs "+i, dateFormat.format(c.getTime()),"#FFFFFF", "red"));
	    			c.add(Calendar.DATE, 1+Integer.parseInt(user.getWorkoutroutine().split("-")[2]));
	    			workoutsEvent.add(new Workoutevent("Push "+i, dateFormat.format(c.getTime()),"#FFFFFF", "0000FF"));
	    		}
	    		
	        	
	        }
	        
	        else if(lastWorkout.getType().equals(TypeWorkout.Pull)) {
	        	int nb = daysInMonth / (3+Integer.parseInt(user.getWorkoutroutine().split("-")[0])+Integer.parseInt(user.getWorkoutroutine().split("-")[1]+Integer.parseInt(user.getWorkoutroutine().split("-")[2])));
	    		
	    		for(int i=1;i<=nb;i++) {
	    			c.add(Calendar.DATE, 1+Integer.parseInt(user.getWorkoutroutine().split("-")[1]));
	    			workoutsEvent.add(new Workoutevent("Legs "+i, dateFormat.format(c.getTime()),"#FFFFFF", "#FFFF00"));
	    			c.add(Calendar.DATE, 1+Integer.parseInt(user.getWorkoutroutine().split("-")[2]));
	    			workoutsEvent.add(new Workoutevent("Push "+i, dateFormat.format(c.getTime()),"#FFFFFF", "red"));
	    			c.add(Calendar.DATE, 1+Integer.parseInt(user.getWorkoutroutine().split("-")[0]));
	    			workoutsEvent.add(new Workoutevent("Pull "+i, dateFormat.format(c.getTime()),"#FFFFFF", "0000FF"));
	    		}
	    		
	        	
	        }
	        
	        else if(lastWorkout.getType().equals(TypeWorkout.Legs)) {
	        	int nb = daysInMonth / (3+Integer.parseInt(user.getWorkoutroutine().split("-")[0])+Integer.parseInt(user.getWorkoutroutine().split("-")[1]+Integer.parseInt(user.getWorkoutroutine().split("-")[2])));
	    		
	    		for(int i=1;i<=nb;i++) {
	    			c.add(Calendar.DATE, 1+Integer.parseInt(user.getWorkoutroutine().split("-")[2]));
	    			workoutsEvent.add(new Workoutevent("Push "+i, dateFormat.format(c.getTime()),"#FFFFFF" ,"#FFFF00"));
	    			c.add(Calendar.DATE, 1+Integer.parseInt(user.getWorkoutroutine().split("-")[0]));
	    			workoutsEvent.add(new Workoutevent("Pull "+i,dateFormat.format(c.getTime()),"#FFFFFF", "red"));
	    			c.add(Calendar.DATE, 1+Integer.parseInt(user.getWorkoutroutine().split("-")[1]));
	    			workoutsEvent.add(new Workoutevent("Legs "+i,dateFormat.format(c.getTime()),"#FFFFFF", "0000FF"));
	    		}
	    		
	        	
	        }
	        
	        
	        
	        	
		}
		  
		return workoutsEvent;
		}
		else
			return null;
		
	}
}
