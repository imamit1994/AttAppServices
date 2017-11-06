package attapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import attapp.model.Representative;
import attapp.services.MongoDbConnection;

@Controller
public class RepresentiveController {
	
	@Autowired
	MongoDbConnection mongo;
	@RequestMapping("/representative")
    public String displayCOBC(Model model) {
    	System.out.println("Inside code of buisness conduct");
    	model.addAttribute("representative", new Representative());
        return "codeofbuisnessconduct";
    }
	
	/*@RequestMapping("/cobconduct")
	public String saveInDb(@ModelAttribute Representative r) {
    	System.out.println(r.getName());
    	System.out.println(r.getUuid());
    	System.out.println(r.getSupervisorName());
    	System.out.println(r.getDate());
    	mongo.inserIntoEmplyee(r.getName(), r.getUuid(), r.getSupervisorName(), r.getDate());
        return "saveform";
   }*/
	@RequestMapping(value="/cobconduct",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> uploadFile(
			@ModelAttribute Representative r) {
	  
	  try {
		  System.out.println("Name:"+r.getName());
		  System.out.println("SuperVisorName:"+r.getSupervisorName());
		  System.out.println("UUID:"+r.getUuid());
		  System.out.println("Date"+r.getDate());
		  mongo.inserIntoEmplyee(r);
	    // Get the filename and build the local file path (be sure that the 
	    // application have write permissions on such directory)
	    String filename = r.getFiles().getOriginalFilename();
	    String directory = "D://temp/";
	    String filepath = Paths.get(directory, filename).toString();
	    
	    // Save the file locally
	    BufferedOutputStream stream =
	        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	    stream.write(r.getFiles().getBytes());
	    stream.close();
	  }
	  catch (Exception e) {
	    System.out.println(e.getMessage());
	    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	  }
	  
	  return new ResponseEntity<>(HttpStatus.OK);
	}
	@RequestMapping("/{uuid}")
	public void operationOnDb(@PathVariable("uuid") String uuid) throws IOException{
		mongo.mongooperation(uuid);
		System.out.println("file deleted succesfully");
		//return "saveform";
	}
}
