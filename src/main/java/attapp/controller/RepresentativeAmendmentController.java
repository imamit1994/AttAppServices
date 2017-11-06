package attapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RepresentativeAmendmentController {
	@RequestMapping("/amendment")
	public String displayCaIAgreement(){
		System.out.println("Inside NDA Amendment");
		return "ndaamendment";
	}
}
