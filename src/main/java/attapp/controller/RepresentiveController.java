package attapp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping("/cobconduct")
	public String saveInDb(@ModelAttribute Representative r) {
    	System.out.println(r.getName());
    	System.out.println(r.getUuid());
    	System.out.println(r.getSupervisorName());
    	System.out.println(r.getDate());
    	mongo.inserIntoEmplyee(r.getName(), r.getUuid(), r.getSupervisorName(), r.getDate());
        return "saveform";
   }
	@RequestMapping("/{uuid}")
	public String operationOnDb(@PathVariable("uuid") String uuid) throws IOException{
		mongo.mongooperation(uuid);
		return "hello";
	}
}
