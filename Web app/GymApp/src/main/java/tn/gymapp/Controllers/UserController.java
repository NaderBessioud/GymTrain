package tn.gymapp.Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import tn.gymapp.Entities.ImageResponse;
import tn.gymapp.Entities.User;
import tn.gymapp.Entities.Workout;
import tn.gymapp.Services.ExerciceService;
import tn.gymapp.Services.FileService;
import tn.gymapp.Services.UserService;
import tn.gymapp.Services.WorkoutService;
import tn.gymapp.dto.Workoutevent;
import tn.gymapp.dto.WorkoutsResponse;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor

public class UserController {
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private UserService service;
	
	@Autowired
	private WorkoutService workoutService;
	
	@Autowired
	private ExerciceService exerciceService;
	
	
	 @PostMapping("/uploadimage")
	    public  ImageResponse uploadFile(@RequestParam(value = "imageFile", required = true) MultipartFile files) {
		 return fileService.upload(files);
	 }
	 
	 @GetMapping(value = "/downloadimage")
	    public ImageResponse download(@RequestParam("image") String file) throws IOException{
		 return fileService.download(file);
	 }
	 
	 @PostMapping("/updateProfile")
	 public User updateProfile(@RequestBody User user) {
		 return service.UpdateUser(user);
				
	 }
	 
	 @GetMapping("/updatePassword")
	 public boolean updatePassword(@RequestParam("pass") String pass,@RequestParam("id") long id) {
		 return service.updatePassword(pass,id);
	 }
	 
	 @PostMapping("/upadateWorkoutRoutine")
	 public User updateWorkoutRoutine(@RequestBody User user) {
		 return service.updateWorkoutRoutine(user);
	 }
	 

	 @GetMapping("/userPerId")
	 public User getUserById(@RequestParam("id") long id) {
		 return service.getUserById(id);
	 }
	 
	 @GetMapping("/checkpass")
	 public boolean checkcpass(@RequestParam("id") long id,@RequestParam("pass") String pass) {
		 return service.checkcpass(pass, id);
	 }
	 
	 @GetMapping("/WorkoutsDate")
	 public List<Workoutevent> getWorkoutsDate(@RequestParam("id") long id) {
		 return workoutService.getWorkoutsDate(id);
	 }
	 
	 @PostMapping("/addWorkout")
	 public Workout addWorkout(@RequestBody Workout workout,@RequestParam("id") long id) {
		 return workoutService.AddWorkout(workout, id); 
				 }
	 
	 @GetMapping("/loadBodyPart")
	 public List<String> loadBodyPart(){
		 return exerciceService.LoadBodyPart();
	 }
	 
	 @GetMapping("/loadMuscleByBodyPart")
	 public List<String> loadMuscleByBodyPart(@RequestParam("part") String label){
		 return exerciceService.LoadMuscleByBodyPart(label);
	 }
	 
	 @GetMapping("/loadExercicesByMuscle")
	 public List<String> loadExercicesByMuscle(@RequestParam("muscle") String label){
		 return exerciceService.LoadExercicesByMuscle(label);
	 }

}
