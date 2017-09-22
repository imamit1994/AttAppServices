package attapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class EmployeeController {
	@RequestMapping("/employee")
    public String displayCompliance(Model model) {
    	System.out.println("Inside display compliance");
        return "index";
    }
}
