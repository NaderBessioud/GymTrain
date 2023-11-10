package tn.gymapp.Services;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClientBuilder;

import tn.gymapp.Entities.ImageResponse;

@Service
public class FileService {

	@Autowired
    BlobClientBuilder client;
	
	public ImageResponse upload(MultipartFile file) {
        if(file != null && file.getSize() > 0) {
            try {
            		
            	
                String fileName = "IronWave_"+ UUID.randomUUID().toString() +file.getOriginalFilename();
                client.blobName(fileName).buildClient().upload(file.getInputStream(),file.getSize());
                return new ImageResponse(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
	
	public ImageResponse uploadImageExercice(MultipartFile file,String label) {
        if(file != null && file.getSize() > 0) {
            try {
            		
            	
                String fileName = "IronWave_"+label ;
                client.blobName(fileName).buildClient().upload(file.getInputStream(),file.getSize());
                return new ImageResponse(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ImageResponse download(String name) {
        try {
        	byte[] content = client.blobName(name).buildClient().downloadContent().toBytes();
        	String encodedImage=Base64.getEncoder().encodeToString(content);
        	encodedImage="data:image/png;base64,"+encodedImage;
        	return new ImageResponse(encodedImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    
    
    
 

    

    

    

 
}
