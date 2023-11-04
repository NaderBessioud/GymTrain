package tn.gymapp.dto;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import tn.gymapp.Entities.Workout;

@Data
@RequiredArgsConstructor
public class WorkoutsResponse {
	
	private List<Workout> Pushs;
	private List<Workout> Pulls;
	private List<Workout> Legs;
	public WorkoutsResponse(List<Workout> pushs, List<Workout> pulls, List<Workout> legs) {
		super();
		Pushs = pushs;
		Pulls = pulls;
		Legs = legs;
	}
	
	

}
