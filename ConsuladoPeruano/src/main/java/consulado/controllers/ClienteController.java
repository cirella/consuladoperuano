package consulado.controllers;

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
import consulado.services.ClienteService;
import consulado.services.DireccionService;
import consulado.services.DistritoService;
import consulado.utils.Constantes;


@Controller
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
    private ClienteService clienteService;
	@Autowired
    private DistritoService distritoService;
	@Autowired
    private DireccionService direccionService;
	
	@Secured({"ROLE_SUPER_ADMIN", "ROLE_ADMIN_TIENDA", "ROLE_ATENCION"})
	@GetMapping("/c-list-cliente")
    public String showListaClientes(Model model){
		
		Constantes constantes=new Constantes();
		model.addAttribute("listaTipodocumento",constantes.ArregloString("TiposDocumento"));
		model.addAttribute("listCliente", clienteService.listAll());
		model.addAttribute("cliente", new Cliente());
		return "/clientes/list-cliente";
    }
	
	@Secured({"ROLE_SUPER_ADMIN", "ROLE_ADMIN_TIENDA", "ROLE_ATENCION"})
	@PostMapping("/do-buscar-cliente-list")
    public String showBuscarListaClientes(@Valid Cliente cliente,Model model){
		
		Constantes constantes=new Constantes();
		model.addAttribute("listaTipodocumento",constantes.ArregloString("TiposDocumento"));
		model.addAttribute("listCliente", clienteService.findByApellidos(cliente.getApellidos()));
		model.addAttribute("cliente", cliente);
		
        return "/clientes/list-cliente";
    }
	
	@Secured({"ROLE_SUPER_ADMIN", "ROLE_ADMIN_TIENDA", "ROLE_ATENCION"})
    @GetMapping("/c-add-cliente")
    public String showNuevoCliente(Model model){
		model.addAttribute("cliente", new Cliente());
		Constantes constantes=new Constantes();
		model.addAttribute("listaTipodocumento",constantes.TiposDocumento);
		return "/clientes/add-cliente";
    }
	
	@Secured({"ROLE_SUPER_ADMIN", "ROLE_ADMIN_TIENDA", "ROLE_ATENCION"})
    @PostMapping("/do-add-cliente")
    public String doNuevoCliente(@Valid Cliente cliente, BindingResult result, Model model){
    	 if (result.hasErrors()) {
             return "/clientes/add-cliente";
         }

    	 cliente=clienteService.save(cliente,true);
    	 Constantes constantes=new Constantes();
 		 model.addAttribute("listaTipodocumento",constantes.ArregloString("TiposDocumento"));
 	  	 model.addAttribute("listCliente", clienteService.listAll());
   		model.addAttribute("cliente", new Cliente());
         return "/clientes/list-cliente";
    }
    
	@Secured({"ROLE_SUPER_ADMIN", "ROLE_ADMIN_TIENDA", "ROLE_ATENCION"})
    @GetMapping("/c-edit-cliente/{id}")
    public String showEditarCliente(@PathVariable("id") long id, Model model) {
        
    	 Cliente cliente = clienteService.findById(id);
        
		 model.addAttribute("cliente", cliente);
		 Constantes constantes=new Constantes();
		 model.addAttribute("listaTipodocumento",constantes.TiposDocumento);
		
		 Direccion direccion=new Direccion();
		 direccion.setCliente(cliente);
		 model.addAttribute("direccion", direccion);
		 model.addAttribute("listDistrito", distritoService.listAll());        
	    
		 model.addAttribute("listDireccion", direccionService.findByClienteId(cliente.getId()));
		 return "/clientes/edit-cliente";
    }
    
	@Secured({"ROLE_SUPER_ADMIN", "ROLE_ADMIN_TIENDA", "ROLE_ATENCION"})
    @PostMapping("/do-edit-cliente")
    public String doEditarCliente(@Valid Cliente cliente, BindingResult result, Model model){
    	if (result.hasErrors()) {
            return "/clientes/edit-cliente";
        }
         
    	clienteService.saveSinFK(cliente);

    	Constantes constantes=new Constantes();
 		model.addAttribute("listaTipodocumento",constantes.ArregloString("TiposDocumento"));
 		model.addAttribute("listCliente", clienteService.listAll());
   		model.addAttribute("cliente", new Cliente());

 		return "/clientes/list-cliente";
    }
    
    
    
	@Secured({"ROLE_SUPER_ADMIN", "ROLE_ADMIN_TIENDA", "ROLE_ATENCION"})
    @GetMapping("/do-delete-cliente/{id}")
    public String doEliminarCliente(@PathVariable("id") long id, Model model) {
        Cliente cliente = clienteService.findById(id);
        clienteService.delete(cliente);
        
        Constantes constantes=new Constantes();
 		model.addAttribute("listaTipodocumento",constantes.ArregloString("TiposDocumento"));
 		model.addAttribute("listCliente", clienteService.listAll());
   		model.addAttribute("cliente", new Cliente());
        return "/clientes/list-cliente";
    }
    
	@Secured({"ROLE_SUPER_ADMIN", "ROLE_ADMIN_TIENDA", "ROLE_ATENCION"})
    @PostMapping("/do-add-direccion-cliente")
    public String doNuevoDireccionCliente(@Valid Direccion direccion, BindingResult result, Model model){
    	if (result.hasErrors()) {
            return "/clientes/edit-cliente";
        }
         


    	Cliente cliente = clienteService.findById(direccion.getCliente().getId());
    	direccion.setCliente(cliente);
    	direccionService.save(direccion);
        
		model.addAttribute("cliente", cliente);
		Constantes constantes=new Constantes();
		model.addAttribute("listaTipodocumento",constantes.TiposDocumento);
	
		Direccion direccion_nueva=new Direccion();
		direccion_nueva.setCliente(cliente);
		model.addAttribute("direccion", direccion_nueva);
		model.addAttribute("listDistrito", distritoService.listAll());        
		 model.addAttribute("listDireccion", direccionService.findByClienteId(cliente.getId()));
			
		return "/clientes/edit-cliente";
    }

	@Secured({"ROLE_SUPER_ADMIN", "ROLE_ADMIN_TIENDA", "ROLE_ATENCION"})
    @GetMapping("/do-delete-direccion-cliente/{id}")
    public String doEliminarDireccionCliente(@PathVariable("id") long id, Model model) {
        Direccion direccion_a_borrar = direccionService.findById(id);
        long id_cliente=direccion_a_borrar.getCliente().getId();
        direccionService.delete(direccion_a_borrar);
        
        
         Cliente cliente = clienteService.findById(id_cliente);
     
		 model.addAttribute("cliente", cliente);
		 Constantes constantes=new Constantes();
		 model.addAttribute("listaTipodocumento",constantes.TiposDocumento);
		
		 Direccion direccion=new Direccion();
		 direccion.setCliente(cliente);
		 model.addAttribute("direccion", direccion);
		 model.addAttribute("listDistrito", distritoService.listAll());        
	    
		 model.addAttribute("listDireccion", direccionService.findByClienteId(cliente.getId()));
		 return "/clientes/edit-cliente";
    }


}
