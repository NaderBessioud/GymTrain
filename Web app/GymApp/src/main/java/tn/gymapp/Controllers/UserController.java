package tn.gymapp.Controllers;

import java.io.IOException;

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
import tn.gymapp.Services.FileService;
import tn.gymapp.Services.UserService;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor

public class UserController {
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private UserService service;
	
	
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
	 

	 @GetMapping("/userPerId")
	 public User getUserById(@RequestParam("id") long id) {
		 return service.getUserById(id);
	 }
	 
	 @GetMapping("/checkpass")
	 public boolean checkcpass(@RequestParam("id") long id,@RequestParam("pass") String pass) {
		 return service.checkcpass(pass, id);
	 }

}
