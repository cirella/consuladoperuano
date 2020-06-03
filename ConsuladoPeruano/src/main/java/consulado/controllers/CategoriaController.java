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

import consulado.entities.Categoria;
import consulado.services.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
    private CategoriaService categoriaService;
	

	   
    @GetMapping("/c-list-categoria")
    public String showListaCategorias(Model model){

    	model.addAttribute("listCategoria", categoriaService.listAll());
        return "/categorias/list-categoria";
    }
    
    @GetMapping("/c-add-categoria")
    public String showNuevoCategoria(Model model){
		model.addAttribute("categoria", new Categoria());
        return "/categorias/add-categoria";
    }
    
    @PostMapping("/do-add-categoria")
    public String doNuevoCategoria(@Valid Categoria categoria, BindingResult result, Model model){
    	 if (result.hasErrors()) {
             return "/categorias/add-categoria";
         }
         
    	 categoriaService.save(categoria);

     	model.addAttribute("listCategoria", categoriaService.listAll());
         return "/categorias/list-categoria";
    }
    
    
    @GetMapping("/c-edit-categoria/{id}")
    public String showEditarCategoria(@PathVariable("id") long id, Model model) {
        Categoria categoria = categoriaService.findById(id);
        model.addAttribute("categoria", categoria);
        return "/categorias/edit-categoria";
    }
	
    @PostMapping("/do-edit-categoria")
    public String doEditCategoria(@Valid Categoria categoria, BindingResult result, Model model){
    	 if (result.hasErrors()) {
             return "/categorias/edit-categoria"+"/"+categoria.getId();
         }
        categoriaService.save(categoria);
      	
        model.addAttribute("listCategoria", categoriaService.listAll());
        return "/categorias/list-categoria";
    }

    @GetMapping("/do-delete-categoria/{id}")
    public String doEliminarCategoria(@PathVariable("id") long id, Model model) {
        Categoria categoria = categoriaService.findById(id);
        categoriaService.delete(categoria);
        model.addAttribute("listCategoria", categoriaService.listAll());
        return "/categorias/list-categoria";
    }
	
}
