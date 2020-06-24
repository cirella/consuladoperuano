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


import consulado.entities.Distrito;
import consulado.entities.Local;

import consulado.services.DistritoService;

import consulado.services.LocalProductoService;
import consulado.services.LocalService;
import consulado.utils.Constantes;


@Controller
@RequestMapping("/locales")
public class LocalController {
	@Autowired
    private DistritoService distritoService;
	@Autowired
    private LocalService localService;
	@Autowired
    private LocalProductoService localProductoService;

	@Secured("ROLE_SUPER_ADMIN")  
    @GetMapping("/c-list-local")
    public String showListaLocales(Model model){

    	model.addAttribute("listLocal", localService.listAll());
        return "/locales/list-local";
    }
    
	@Secured("ROLE_SUPER_ADMIN")  
    @GetMapping("/c-add-local")
    public String showNuevoLocal(Model model){
		model.addAttribute("local", new Local());
        return "/locales/add-local";
    }
    
	@Secured("ROLE_SUPER_ADMIN")  
    @PostMapping("/do-add-local")
    public String doNuevoLocal(@Valid Local local, BindingResult result, Model model){
    	 if (result.hasErrors()) {
             return "/locales/add-local";
         }
         
    	 local=localService.save(local);
    	 localProductoService.cargaLocalProductoParaLocal(local);

    	 model.addAttribute("listLocal", localService.listAll());
         return "/locales/list-local";
    }
    
	@Secured("ROLE_SUPER_ADMIN")  
    @GetMapping("/c-edit-local/{id}")
    public String showEditarLocal(@PathVariable("id") long id, Model model) {
        Local local = localService.findById(id);
        model.addAttribute("local", local);
        model.addAttribute("distrito", new Distrito());
        model.addAttribute("listDistrito_por_asignar", distritoService.findByLocalIdNull());
        model.addAttribute("listDistrito_asignado", distritoService.findByLocalId(id));
        return "/locales/edit-local";
    }
    
	@Secured("ROLE_SUPER_ADMIN")  
    @PostMapping("/do-edit-local")
    public String doEditLocal(@Valid Local local, BindingResult result, Model model){
    	 if (result.hasErrors()) {
             return "/locales/edit-local";
         }
         
    	 localService.save(local);

    	 model.addAttribute("listLocal", localService.listAll());
         return "/locales/list-local";
    }
    
	@Secured("ROLE_SUPER_ADMIN")  
    @PostMapping("/do-asociar-local-distrito")
    public String doAsociarLocal(@Valid Distrito distrito, Long idlocal,BindingResult result, Model model){
		distritoService.asociaDistritoConLocal(distrito.getId(),idlocal);
    	
    	Local local = localService.findById(idlocal);
        model.addAttribute("local", local);        
        model.addAttribute("distrito", new Distrito());        
        model.addAttribute("listDistrito_por_asignar", distritoService.findByLocalIdNull());        
        model.addAttribute("listDistrito_asignado", distritoService.findByLocalId(local.getId()));        
        
        return "/locales/edit-local";
    }
    
    
	@Secured("ROLE_SUPER_ADMIN")  
    @GetMapping("/do-desasociar-local-distrito/{id}")
    public String doDesasociarLocal(@PathVariable("id") long id, Model model){
		
    	long idlocal=distritoService.findById(id).getLocal().getId();
    	distritoService.desasociaDistritoDeLocal(id);
    	
    	//throw new Exception("Exception message");
    	
    	Local local = localService.findById(idlocal);
        model.addAttribute("local", local);        
        model.addAttribute("distrito", new Distrito());        
        model.addAttribute("listDistrito_por_asignar", distritoService.findByLocalIdNull());        
        model.addAttribute("listDistrito_asignado", distritoService.findByLocalId(idlocal));        
        
        return "/locales/edit-local";
    }
    
	@Secured("ROLE_SUPER_ADMIN")  
    @GetMapping("/do-delete-local/{id}")
    public String doEliminarLocal(@PathVariable("id") long id, Model model) {
        Local local = localService.findById(id);
        localService.delete(local);
        
   	 model.addAttribute("listLocal", localService.listAll());
        return "/locales/list-local";
    }
    
    
	@Secured({"ROLE_SUPER_ADMIN","ROLE_ADMIN_TIENDA"})  
    @GetMapping("/c-edit-local-stock/{id}")
    public String showEditarLocalStock(@PathVariable("id") long id, Model model, HttpSession session) {
        
		Constantes constantes = new Constantes();
		Long tipo_usuario_logeado=Integer.toUnsignedLong((int)session.getAttribute("tipo_usuario_logeado"));
		Long id_local_empleado=Integer.toUnsignedLong((int)session.getAttribute("id_local_empleado"));
		
		if (constantes.findByTipo("TiposUsuario", "Administrador")==tipo_usuario_logeado) {
	        if (id!=id_local_empleado) {
	    		return "/error";				    		
	        }	        
		}
		
    	Local local = localService.findById(id);
    	localProductoService.cargaLocalProductoParaLocal(local);
    	local = localService.findById(id);
    	
    	int[] indexes = new int[local.getLocalesproductos().size()];
		for(int i=0;i<local.getLocalesproductos().size() ; i++) {
			indexes[i]=i;
		}
		
    	model.addAttribute("indexes", indexes);
        model.addAttribute("local", local);
        return "/locales/edit-local-stock";
        
    }
    
	@Secured("ROLE_SUPER_ADMIN")  
    @PostMapping("/do-edit-local-stock")
    public String doEditLocalProducto(@Valid Local local, BindingResult result, Model model){
    	 if (result.hasErrors()) {
             return "/locales/edit-local-producto";
         }
         
    	//System.out.print(local.toString()+"\n\r");
    	localProductoService.actualizaStockLocal(local);

    	 model.addAttribute("listLocal", localService.listAll());
         return "/locales/list-local";
    }
    
    
    
}
