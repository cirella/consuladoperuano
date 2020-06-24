package consulado.controllers;

import java.util.ArrayList;
import java.util.List;

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


import consulado.entities.Empleado;
import consulado.entities.Local;
import consulado.entities.Usuario;
import consulado.services.EmpleadoService;
import consulado.services.LocalService;
import consulado.services.UsuarioService;
import consulado.utils.Constantes;
import consulado.utils.Tipo;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

	@Autowired
    private EmpleadoService empleadoService;
	@Autowired
    private UsuarioService usuarioService;
	@Autowired
    private LocalService localService;
	
	@Secured({"ROLE_SUPER_ADMIN", "ROLE_ADMIN_TIENDA"})
	@GetMapping("/c-list-empleado")
    public String showListaEmpleados(Model model, HttpSession session){
		
		Constantes constantes=new Constantes();
		model.addAttribute("listaTipoempleado",constantes.ArregloString("TiposEmpleado"));
		model.addAttribute("listaTipodocumento",constantes.ArregloString("TiposDocumento"));
		
		if (Long.parseLong(session.getAttribute("tipo_usuario_logeado").toString())==constantes.findByTipo("TiposUsuario", "SuperAdmin")) {
			model.addAttribute("listEmpleado", empleadoService.listAll());
			//System.out.println("c-list-empleado con SuperAdmin");
		} else {
			Empleado empleado=empleadoService.findById(Long.parseLong(session.getAttribute("id_persona_logeado").toString()));
			model.addAttribute("listEmpleado", empleadoService.listByIdLocal(empleado.getLocal().getId()));		
			//System.out.println("c-list-empleado con Administrador");
		}
        
		return "/empleados/list-empleado";
    }
	
	@Secured({"ROLE_SUPER_ADMIN", "ROLE_ADMIN_TIENDA"})
    @GetMapping("/c-add-empleado")
    public String showNuevoEmpleado(Model model, HttpSession session){
		
		Empleado empleado=empleadoService.findById(Long.parseLong(session.getAttribute("id_persona_logeado").toString()));
		
		model.addAttribute("empleado", new Empleado());
		Constantes constantes=new Constantes();
		
		List<Tipo> tiposEmpleado=constantes.TiposEmpleado;
		if (Long.parseLong(session.getAttribute("tipo_usuario_logeado").toString())!=constantes.findByTipo("TiposUsuario", "SuperAdmin")) {
			tiposEmpleado.remove(0);
			List<Local> locales=new ArrayList<>();
			locales.add(localService.findById(empleado.getLocal().getId()));
			model.addAttribute("listaLocal", locales);
		} else {
			model.addAttribute("listaLocal", localService.listAll());
		}
		
		model.addAttribute("listaTipoempleado",tiposEmpleado);
		model.addAttribute("listaTipodocumento",constantes.TiposDocumento);
	    return "/empleados/add-empleado";
    }
    
    
	@Secured({"ROLE_SUPER_ADMIN", "ROLE_ADMIN_TIENDA"})
    @PostMapping("/do-add-empleado")
    public String doNuevoEmpleado(@Valid Empleado empleado, BindingResult result, Model model){
    	if (result.hasErrors()) {
            return "/empleados/add-empleado";
        }
    	
    	if (usuarioService.findByNombreUsuario(empleado.getEmail())!=null) {
    		model.addAttribute("mensaje","Usuario y Email duplicado");
    		return "/empleados/add-empleado";
    	}
    	
    	
    	
    	empleado=empleadoService.save(empleado,true);
    	empleado.getUsuario().setPassword(empleado.getNumerodocumento());
    	
    	Constantes constantes=new Constantes();

    	
    	
    	
    	model.addAttribute("listaTipoempleado",constantes.ArregloString("TiposEmpleado"));
 		model.addAttribute("listaTipodocumento",constantes.ArregloString("TiposDocumento"));
 		model.addAttribute("empleado",empleado);
 		//model.addAttribute("listEmpleado", empleadoService.listAll());
 		
    	return "/empleados/show-empleado-usuario";
        // return "/empleados/list-empleado";
    }
    
    
	@Secured({"ROLE_SUPER_ADMIN", "ROLE_ADMIN_TIENDA"})    
	@GetMapping("/c-edit-empleado/{id}")
    public String showEditarEmpleado(@PathVariable("id") long id, Model model, HttpSession session) {
        Empleado empleado = empleadoService.findById(id);
        Constantes constantes=new Constantes();
		
        
        List<Tipo> tiposEmpleado=constantes.TiposEmpleado;
		if (Long.parseLong(session.getAttribute("tipo_usuario_logeado").toString())!=constantes.findByTipo("TiposUsuario", "SuperAdmin")) {
			tiposEmpleado.remove(0);
			List<Local> locales=new ArrayList<>();
			locales.add(localService.findById(empleado.getLocal().getId()));
			model.addAttribute("listaLocal", locales);
		} else {
			model.addAttribute("listaLocal", localService.listAll());
		}
        
		model.addAttribute("empleado", empleado);
		model.addAttribute("usuario", empleado.getUsuario());
		
		model.addAttribute("listaTipoempleado",tiposEmpleado);
		model.addAttribute("listaTipodocumento",constantes.TiposDocumento);
		return "/empleados/edit-empleado";
    }
    
	@Secured({"ROLE_SUPER_ADMIN", "ROLE_ADMIN_TIENDA"})
    @PostMapping("/do-edit-empleado")
    public String doEditarEmpleado(@Valid Empleado empleado, BindingResult result, Model model, HttpSession session){
    	if (result.hasErrors()) {
            return "/empleados/edit-empleado";
        }
         
    	Empleado empleado_anterior=empleadoService.findById(empleado.getId());
    	if (empleado_anterior.getEmail()!=empleado.getEmail()) {
    		if (usuarioService.findByNombreUsuario(empleado.getEmail())!=null) {
        		model.addAttribute("mensaje","Usuario y Email duplicado");
        		return "/empleados/edit-empleado";
        	}	
    	}
 
    	empleadoService.saveSinFK(empleado);
    	
    	Constantes constantes=new Constantes();
    	
    	if (Long.parseLong(session.getAttribute("tipo_usuario_logeado").toString())==constantes.findByTipo("TiposUsuario", "SuperAdmin")) {
			model.addAttribute("listEmpleado", empleadoService.listAll());
			//System.out.println("c-list-empleado con SuperAdmin");
		} else {
			Empleado empleado_2=empleadoService.findById(Long.parseLong(session.getAttribute("id_persona_logeado").toString()));
			model.addAttribute("listEmpleado", empleadoService.listByIdLocal(empleado_2.getLocal().getId()));		
			//System.out.println("c-list-empleado con Administrador");
		}
    	
    	
    	
		model.addAttribute("listaTipoempleado",constantes.ArregloString("TiposEmpleado"));
		model.addAttribute("listaTipodocumento",constantes.ArregloString("TiposDocumento"));
 		return "/empleados/list-empleado";
    }
    
	@Secured({"ROLE_SUPER_ADMIN", "ROLE_ADMIN_TIENDA"})
    @PostMapping("/do-edit-empleado-password")
    public String doEditarEmpleadoPassword(@Valid Usuario usuario, BindingResult result, Model model){
    	if (result.hasErrors()) {
            return "/empleados/edit-empleado";
        }
    	usuarioService.cambiaPassword(usuario.getId(),usuario.getPassword());

    	Constantes constantes=new Constantes();
		model.addAttribute("listaTipoempleado",constantes.ArregloString("TiposEmpleado"));
		model.addAttribute("listaTipodocumento",constantes.ArregloString("TiposDocumento"));
 		model.addAttribute("listEmpleado", empleadoService.listAll());
        return "/empleados/list-empleado";
    }
    
    
	@Secured({"ROLE_SUPER_ADMIN", "ROLE_ADMIN_TIENDA"})
    @GetMapping("/do-delete-empleado/{id}")
    public String doEliminarEmpleado(@PathVariable("id") long id, Model model) {
        Empleado empleado = empleadoService.findById(id);
        empleadoService.delete(empleado);
        
        Constantes constantes=new Constantes();
		model.addAttribute("listaTipoempleado",constantes.ArregloString("TiposEmpleado"));
		model.addAttribute("listaTipodocumento",constantes.ArregloString("TiposDocumento"));
 		model.addAttribute("listEmpleado", empleadoService.listAll());
        return "/empleados/list-empleado";
    }
    
	
}
