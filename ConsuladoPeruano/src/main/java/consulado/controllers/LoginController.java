package consulado.controllers;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import consulado.entities.Usuario;
import consulado.services.UsuarioService;


@Controller
public class LoginController {
    
	@Autowired
    private UsuarioService usuarioService;

   
    @GetMapping("/")
    public String showIndex(Model model){
    	Usuario usuario=new Usuario();
    	model.addAttribute("usuario_a_validar",usuario);
        return "index";
    }

    @PostMapping("/doValidaUsuario")
    public String doValidaUsuario(@Valid Usuario usuario_valido, BindingResult result, Model model, HttpSession session){ 	   
    	if (result.hasErrors()) {
        	model.addAttribute("usuario_a_validar",new Usuario() );
            return "index";
        }
    	usuario_valido=usuarioService.validaUsuarioPassword(usuario_valido.getNombreusuario(), usuario_valido.getPassword());
    	if (usuario_valido==null) {
        	model.addAttribute("usuario_a_validar",new Usuario() );
    		return "index";
    	}
 //   	model.addAttribute("usuario_logeado", usuario_valido);
		session.setAttribute("tipo_usuario_logeado", usuario_valido.getTipo());
 
        return "home";
    }
    
    @GetMapping("/showHome")
    public String showHome(Model model){
    	
    	//model.addAttribute("usuario",new Usuario() );
        return "home";
    }

}
