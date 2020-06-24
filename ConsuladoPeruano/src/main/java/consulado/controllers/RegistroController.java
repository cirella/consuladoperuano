package consulado.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import consulado.entities.Cliente;
import consulado.entities.Direccion;
import consulado.entities.Rol;
import consulado.entities.Usuario;
import consulado.services.ClienteService;
import consulado.services.DireccionService;
import consulado.services.DistritoService;
import consulado.services.UsuarioService;
import consulado.utils.Constantes;

@Controller
@RequestMapping("/registro")
public class RegistroController {

	@Autowired
	private ClienteService clienteService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private DireccionService direccionService;
	@Autowired
	private DistritoService distritoService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/c-add-usuario")
	public String showNuevoUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "/registro/add-usuario";
	}

	@PostMapping("/do-add-usuario")
	public String doNuevoUsuario(@Valid Usuario usuario, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			return "/registro/add-usuario";
		}
		if (usuarioService.findByNombreUsuario(usuario.getNombreusuario()) == null) {
			Constantes constantes = new Constantes();

			session.setAttribute("nombre_usuario_nuevo", usuario.getNombreusuario());
			session.setAttribute("password_usuario_nuevo", passwordEncoder.encode(usuario.getPassword()));
			session.setAttribute("tipo_usuario_nuevo", constantes.findByTipo("TiposUsuario", "Cliente"));
			
			model.addAttribute("cliente", new Cliente());
			model.addAttribute("listaTipodocumento", constantes.TiposDocumento);
			
			return "/registro/add-cliente";
		} else {
			model.addAttribute("mensaje", "Usuario duplicado");
			return "/registro/add-usuario";
		}
	}
	

	
	@PostMapping("/do-add-cliente")
	public String doNuevoCliente(@Valid Cliente cliente, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			return "/registro/add-cliente";
		}
		Constantes constantes = new Constantes();
		
		Usuario usuario = new Usuario();
		usuario.setNombreusuario(session.getAttribute("nombre_usuario_nuevo").toString());
		usuario.setPassword(session.getAttribute("password_usuario_nuevo").toString());
		usuario.setTipo(Integer.parseInt(session.getAttribute("tipo_usuario_nuevo").toString()));
		
		Rol rol=new Rol();
		rol.setUsuario(usuario);
		rol.setRol(constantes.findById("TiposRol", constantes.findByTipo("TiposUsuario", "Cliente")));
		List<Rol> nuevos_roles=new ArrayList<Rol>();
		nuevos_roles.add(rol);
		usuario.setRoles(nuevos_roles);
		usuario = usuarioService.save(usuario);
		

		cliente.setUsuario(usuario);
		cliente = clienteService.save(cliente);
		session.removeAttribute("nombre_usuario_nuevo");
		session.removeAttribute("password_usuario_nuevo");
		session.removeAttribute("tipo_usuario_nuevo");
		session.setAttribute("id_cliente_nuevo",cliente.getId());
		
		Direccion direccion_nueva=new Direccion();
		model.addAttribute("direccion", direccion_nueva);
		model.addAttribute("listDistrito", distritoService.findByLocalIsNotNull());    
		model.addAttribute("cliente", cliente);		
		
		return "/registro/add-direccion";
	}

	@PostMapping("/do-add-direccion-cliente")
    public String doNuevoDireccionCliente(@Valid Direccion direccion, BindingResult result, Model model, HttpSession session){
    	if (result.hasErrors()) {
            return "/registro/add-direccion";
        }
         
    	Long id_cliente_nuevo=Long.parseLong(session.getAttribute("id_cliente_nuevo").toString());
    	Cliente cliente = clienteService.findById(id_cliente_nuevo);
    	direccion.setCliente(cliente);
    	direccionService.save(direccion);
        
		model.addAttribute("cliente", cliente);
		
		
		Direccion direccion_nueva=new Direccion();
		direccion_nueva.setCliente(cliente);
		model.addAttribute("direccion", direccion_nueva);
		model.addAttribute("listDistrito", distritoService.findByLocalIsNotNull());        
		model.addAttribute("listDireccion", direccionService.findByClienteId(cliente.getId()));
		
		return "/registro/add-direccion";
    }
	
	
	@GetMapping("/do-delete-direccion-cliente/{id}")
    public String doEliminarDireccionCliente(@PathVariable("id") long id, Model model) {
        
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
		 return "/registro/add-direccion";
    }
    
	@PostMapping("/do-fin-registro")
    public String doFinRegistro( HttpSession session){
		session.removeAttribute("id_cliente_nuevo");	
				
		return "/index";
    }
	
}
