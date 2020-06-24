package consulado.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//import consulado.entities.Usuario;

@Controller
@RequestMapping("/")
public class IndexController {

    
    @GetMapping(value={"/index","/"})
    public String showIndex(Model model,HttpServletRequest request){
    	//System.out.println("Por aqui showIndex");
    	
    	try {
			//objeto de Spring especial para cerrar sesión
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		//cerrando sesión
		logoutHandler.logout(request, null, null);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return "index";
		}
		return "index";
    	
    	
    	
    }
	
}
