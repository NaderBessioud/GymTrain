package tn.gymapp.dto;

import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Workoutevent {
	private String title;
	private String date;
	private String TextColor;
	private String backgroundColor;
	public Workoutevent(String title, String date, String textColor, String backgroundColor) {
		super();
		this.title = title;
		this.date = date;
		TextColor = textColor;
		this.backgroundColor = backgroundColor;
	}

	
	
	

	
	
	

}
