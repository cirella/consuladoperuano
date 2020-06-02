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

import consulado.entities.Distrito;
import consulado.services.DistritoService;


@Controller
@RequestMapping("/distritos")
public class DistritoController {
	
	@Autowired
    private DistritoService distritoService;

   
    @GetMapping("/c-list-distrito")
    public String showListaDistritos(Model model){

    	model.addAttribute("listDistrito", distritoService.listAll());
        return "/distritos/list-distrito";
    }
    
    @GetMapping("/c-add-distrito")
    public String showNuevoDistrito(Model model){
		model.addAttribute("distrito", new Distrito());
        return "/distritos/add-distrito";
    }
    
    @PostMapping("/do-add-distrito")
    public String doNuevoDistrito(@Valid Distrito distrito, BindingResult result, Model model){
    	 if (result.hasErrors()) {
             return "/distritos/add-distrito";
         }
         
    	 distritoService.save(distrito);

     	model.addAttribute("listDistrito", distritoService.listAll());
         return "/distritos/list-distrito";
    }
    
    
    @GetMapping("/c-edit-distrito/{id}")
    public String showEditarDistrito(@PathVariable("id") long id, Model model) {
        Distrito distrito = distritoService.findById(id);
        model.addAttribute("distrito", distrito);
        return "/distritos/edit-distrito";
    }
	
    @PostMapping("/do-edit-distrito")
    public String doEditDistrito(@Valid Distrito distrito, BindingResult result, Model model){
    	 if (result.hasErrors()) {
             return "/distritos/edit-distrito"+"/"+distrito.getId();
         }
        distritoService.save(distrito);
      	
        model.addAttribute("listDistrito", distritoService.listAll());
        return "/distritos/list-distrito";
    }

    @GetMapping("/do-delete-distrito/{id}")
    public String doEliminarDistrito(@PathVariable("id") long id, Model model) {
        Distrito distrito = distritoService.findById(id);
        distritoService.delete(distrito);
        model.addAttribute("listDistrito", distritoService.listAll());
        return "/distritos/list-distrito";
    }
	
}
