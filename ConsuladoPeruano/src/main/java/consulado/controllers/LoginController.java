package consulado.controllers;


import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import consulado.entities.Empleado;

import consulado.entities.Usuario;
import consulado.services.ClienteService;
import consulado.services.EmpleadoService;
import consulado.services.LocalProductoService;

import consulado.services.UsuarioService;
import consulado.utils.Constantes;


@Controller
@RequestMapping
public class LoginController {
    
	@Autowired
    private UsuarioService usuarioService;
    
	@Autowired
    private EmpleadoService empleadoService;
    
	@Autowired
    private ClienteService clienteService;
	
	@Autowired
    private LocalProductoService localproductoService;

  
	@GetMapping(value = { "/login"})
    public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash, HttpSession session, HttpServletRequest httpServletRequest) {

		Constantes constantes = new Constantes();
		
    	if (principal != null) {
    		
    		Usuario usuario=usuarioService.findByNombreUsuario(httpServletRequest.getRemoteUser());
    		//System.out.println(usuario.toString());
    		session.setAttribute("id_usuario_logeado", usuario.getId());
    		session.setAttribute("tipo_usuario_logeado", usuario.getTipo());
    		session.setAttribute("nombre_tipo_usuario_logeado", constantes.findById("TiposUsuario", usuario.getTipo()));
    		
    		session.setAttribute("nombre_usuario_logeado", usuario.getNombreusuario());
    		if (usuario.getTipo()==constantes.findByTipo("TiposUsuario", "Cliente")) {
    			session.setAttribute("id_persona_logeado", clienteService.findByIdUsuario(usuario.getId()).getId());
    			session.setAttribute("id_local_empleado", 0);
        			
        	} else {
        		if (usuario.getTipo()!=constantes.findByTipo("TiposUsuario", "SuperAdmin")) {
            		Empleado empleado=empleadoService.findByIdUsuario(usuario.getId());
        			session.setAttribute("id_persona_logeado",empleado.getId() );
            		session.setAttribute("id_local_empleado", empleado.getLocal().getId());
            		localproductoService.cargaLocalProductoParaLocal(empleado.getLocal());
        		} else {
        			session.setAttribute("id_persona_logeado", 0);
        			session.setAttribute("id_local_empleado", 0);
            	}	
       		}
    		
    		
    		return "redirect:/home";

		}

		if (error != null) {
			model.addAttribute("error",
					"Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
		}

		if (logout != null) {
			model.addAttribute("success", "Ha cerrado sesión con éxito!");
		 	
	    	try {
				//objeto de Spring especial para cerrar sesión
			SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
			//cerrando sesión
			logoutHandler.logout(httpServletRequest, null, null);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				return "logout";
			}
			return "logout";
			
		}

		return "login";
    }
 
    
   /* 
    
    @PostMapping("/doValidaUsuario2")
    public String doValidaUsuario2(@Valid Usuario usuario_valido, BindingResult result, Model model, HttpSession session){ 	   
     	 System.out.println("A VER 2");
    	  
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
 System.out.println("A VER");
        return "home";
    }
 */
     
    /*
    @GetMapping("/showHome")
    public String showHome(Model model){
    	
    	//model.addAttribute("usuario",new Usuario() );
        return "home";
    }
*/
}
