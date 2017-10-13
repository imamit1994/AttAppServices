package attapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RepresentiveController {
	@RequestMapping("/representative")
    public String displayCOBC(Model model) {
    	System.out.println("Inside code of buisness conduct");
        return "codeofbuisnessconduct";
    }
}
