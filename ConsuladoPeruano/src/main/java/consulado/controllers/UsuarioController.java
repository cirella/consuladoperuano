package consulado.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import consulado.entities.Cliente;
import consulado.entities.Direccion;
import consulado.entities.Usuario;
import consulado.services.ClienteService;
import consulado.services.DireccionService;
import consulado.services.DistritoService;
import consulado.services.UsuarioService;
import consulado.utils.Constantes;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
    private UsuarioService usuarioService;
	@Autowired
    private ClienteService clienteService;
	@Autowired
    private DistritoService distritoService;
	@Autowired
    private DireccionService direccionService;
	
	@GetMapping("/c-edit-my-password")
    public String showEditarPassword(Model model, HttpSession session) {
		Long id_usuario_logeado=(Long)session.getAttribute("id_usuario_logeado");
		Usuario usuario=usuarioService.findByIdUsuario(id_usuario_logeado);
		model.addAttribute("usuario", usuario);
		
		return "/usuarios/edit-my-password";
    }
	
	
	@PostMapping("/do-edit-my-password")
    public String doEditarEmpleadoPassword(@Valid Usuario usuario, BindingResult result, Model model){
    	if (result.hasErrors()) {
            return "/usuarios/edit-my-password";
        }
     	usuarioService.cambiaPassword(usuario.getId(),usuario.getPassword());
        return "/home";
    }
    
	@Secured("ROLE_CLIENTE")
	@GetMapping("/c-edit-my-data")
    public String showEditarDatos(Model model, HttpSession session) {
		Long id_persona_logeado=(Long)session.getAttribute("id_persona_logeado");
		
		Cliente cliente = clienteService.findById(id_persona_logeado);
        
		 model.addAttribute("cliente", cliente);
		 Constantes constantes=new Constantes();
		 model.addAttribute("listaTipodocumento",constantes.TiposDocumento);
		
		  return "/usuarios/edit-my-data";
    }
	
	@Secured("ROLE_CLIENTE")	
    @PostMapping("/do-edit-my-data")
    public String doEditarCliente(@Valid Cliente cliente, BindingResult result, Model model){
    	if (result.hasErrors()) {
            return "/usuarios/edit-my-data";
        }
         
    	clienteService.saveSinFK(cliente);

 		return "home";
    }
    
    
	@Secured("ROLE_CLIENTE")
	@GetMapping("/c-edit-my-direccion")
    public String showEditarDireccion(Model model, HttpSession session) {
		Long id_persona_logeado=(Long)session.getAttribute("id_persona_logeado");
		
		Cliente cliente = clienteService.findById(id_persona_logeado);
        
		Direccion direccion_nueva=new Direccion();
		model.addAttribute("listDistrito", distritoService.findByLocalIsNotNull());    
		model.addAttribute("cliente", cliente);		
		
		
		direccion_nueva.setCliente(cliente);
		model.addAttribute("direccion", direccion_nueva);
		model.addAttribute("listDireccion", direccionService.findByClienteId(cliente.getId()));
		
		  return "/usuarios/edit-my-direccion";
    }
	
	@Secured("ROLE_CLIENTE")
	@PostMapping("/do-edit-my-direccion")
    public String doEditarDireccion(@Valid Direccion direccion, BindingResult result, Model model, HttpSession session) {
	   	if (result.hasErrors()) {
            return "/usuarios/edit-my-direccion";
        }
		Long id_persona_logeado=(Long)session.getAttribute("id_persona_logeado");
		Cliente cliente = clienteService.findById(id_persona_logeado);
    	direccion.setCliente(cliente);
    	direccionService.save(direccion);
        
    	Direccion direccion_nueva=new Direccion();
		model.addAttribute("listDistrito", distritoService.listAll());    
		model.addAttribute("cliente", cliente);		
		
		
		direccion_nueva.setCliente(cliente);
		model.addAttribute("direccion", direccion_nueva);
		model.addAttribute("listDireccion", direccionService.findByClienteId(cliente.getId()));
		
		  return "/usuarios/edit-my-direccion";
    }
	
	
	@Secured("ROLE_CLIENTE")	
	@GetMapping("/do-delete-my-direccion/{id}")
    public String doEliminarDireccion(@PathVariable("id") long id, Model model) {
        
		Direccion direccion_a_borrar = direccionService.findById(id);
        long id_cliente=direccion_a_borrar.getCliente().getId();
        direccionService.delete(direccion_a_borrar);
        
        
         Cliente cliente = clienteService.findById(id_cliente);
     
		 model.addAttribute("cliente", cliente);
		
		 Direccion direccion=new Direccion();
		 direccion.setCliente(cliente);
		 model.addAttribute("direccion", direccion);
		 model.addAttribute("listDistrito", distritoService.findByLocalIsNotNull());        
	    
		 model.addAttribute("listDireccion", direccionService.findByClienteId(cliente.getId()));
		 return "/usuarios/edit-my-direccion";
    }
	
}
