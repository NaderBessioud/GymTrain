package tn.gymapp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import tn.gymapp.Entities.ImageResponse;
import tn.gymapp.Services.FileService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

	@Autowired
	private FileService fileService;
	
	
	 @PostMapping("/uploadimageExercice")
	    public  ImageResponse uploadimageExercice(@RequestParam(value = "imageFile", required = true) MultipartFile files,@RequestParam("label") String label) {
		 return fileService.uploadImageExercice(files,label);
	 }

}
