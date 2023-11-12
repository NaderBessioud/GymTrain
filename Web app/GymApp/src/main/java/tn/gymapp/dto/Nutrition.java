package tn.gymapp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Nutrition {
	
	private double protein;
	private double carbs;
	private double fats;
	private double callories;

}
