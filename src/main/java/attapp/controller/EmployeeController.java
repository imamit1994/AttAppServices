package attapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import attapp.services.MongoDBSaveBinaryFile;
@Controller
public class EmployeeController {
	
	@Autowired
	MongoDBSaveBinaryFile mdsbf;
	@RequestMapping("/employee")
    public String displayCompliance(Model model) {
    	System.out.println("Inside display compliance");
        return "index";
    }
	@RequestMapping("/saveform")
    public String saveForm() throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("Inside save form");
		mdsbf.savefileinmongodb();
		return "saveform";
    	   }
	@RequestMapping("/retrievepdf")
    public String retrieveForm() {
		System.out.println("Inside retrieve form");
		mdsbf.retrievefilefromDb();
		return "saveform";
    	   }
	
}
