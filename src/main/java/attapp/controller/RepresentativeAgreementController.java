package attapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RepresentativeAgreementController {
	
	@RequestMapping("/caiagreement")
	public String displayCaIAgreement(){
		System.out.println("Inside Confidentiality and Invention Agreement");
		return "confandinvenagreement";
	}

}
