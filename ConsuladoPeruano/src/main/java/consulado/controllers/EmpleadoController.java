package consulado.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import consulado.entities.Empleado;
import consulado.services.EmpleadoService;
import consulado.services.LocalService;

import consulado.utils.Constantes;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

	@Autowired
    private EmpleadoService empleadoService;
	@Autowired
    private LocalService localService;
	
	@GetMapping("/c-list-empleado")
    public String showListaEmpleados(Model model){

    	model.addAttribute("listEmpleado", empleadoService.listAll());
        return "/empleados/list-empleado";
    }
	
    @GetMapping("/c-add-empleado")
    public String showNuevoEmpleado(Model model){
		model.addAttribute("empleado", new Empleado());
		model.addAttribute("listaLocal", localService.listAll());
		Constantes constantes=new Constantes();
		model.addAttribute("listaTipoempleado",constantes.TiposEmpleado);
		model.addAttribute("listaTipodocumento",constantes.TiposDocumento);
		
		
        return "/empleados/add-empleado";
    }
    
    
    
    @PostMapping("/do-add-empleado")
    public String doNuevoEmpleado(@Valid Empleado empleado, BindingResult result, Model model){
    	 if (result.hasErrors()) {
             return "/empleados/add-empleado";
         }
         
    	 empleado=empleadoService.save(empleado,true);
    	 model.addAttribute("empleado", empleado);
    	 return "/empleados/show-empleado-usuario";
    }
    
    
    
    
    @GetMapping("/c-edit-empleado/{id}")
    public String showEditarEmpleado(@PathVariable("id") long id, Model model) {
        Empleado empleado = empleadoService.findById(id);
        
		model.addAttribute("empleado", empleado);
		model.addAttribute("listaLocal", localService.listAll());
		Constantes constantes=new Constantes();
		model.addAttribute("listaTipoempleado",constantes.TiposEmpleado);
		model.addAttribute("listaTipodocumento",constantes.TiposDocumento);
		
        return "/empleados/edit-empleado";
    }
    
    
    @PostMapping("/do-edit-empleado")
    public String doEditarEmpleado(@Valid Empleado empleado, BindingResult result, Model model){
    	if (result.hasErrors()) {
            return "/empleados/edit-empleado";
        }
         
    	empleadoService.saveSinFK(empleado);

     	model.addAttribute("listEmpleado", empleadoService.listAll());
        return "/empleados/list-empleado";
    }
    
    
    
    
    
    @GetMapping("/do-delete-empleado/{id}")
    public String doEliminarEmpleado(@PathVariable("id") long id, Model model) {
        Empleado empleado = empleadoService.findById(id);
        empleadoService.delete(empleado);
        
    	model.addAttribute("listEmpleado", empleadoService.listAll());
        return "/empleados/list-empleado";
    }
    
	
}
