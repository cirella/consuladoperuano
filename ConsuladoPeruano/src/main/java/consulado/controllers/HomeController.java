package consulado.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//import consulado.entities.Usuario;

@Controller
//@RequestMapping("/")
public class HomeController {

    
    @GetMapping("/home")
    public String showHome(Model model){
    	//System.out.println("Por aqui showHome");
    	return "home";
    }
	
}
