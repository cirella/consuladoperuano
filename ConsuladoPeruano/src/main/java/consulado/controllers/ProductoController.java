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


import consulado.entities.Producto;
import consulado.services.CategoriaService;

import consulado.services.ProductoService;
import consulado.utils.Constantes;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	@Autowired
	private ProductoService productoService;
	@Autowired
	private CategoriaService categoriaService;
	
	   
    @GetMapping("/c-list-producto")
    public String showListaProductos(Model model){
    	
		Constantes constantes=new Constantes();
		model.addAttribute("listaTipoactivo",constantes.ArregloStringTiposActivo());
		model.addAttribute("listProducto", productoService.listAll());
        return "/productos/list-producto";
    }
    
    @GetMapping("/c-add-producto")
    public String showNuevoProducto(Model model){
    	
    	Constantes constantes=new Constantes();
		model.addAttribute("producto", new Producto());
		model.addAttribute("listaTipoactivo",constantes.TiposActivo);
		model.addAttribute("listaCategoria",categoriaService.listAll());
		
		
        return "/productos/add-producto";
    }
    
    
    @PostMapping("/do-add-producto")
    public String doNuevoProducto(@Valid Producto producto, BindingResult result, Model model){
    	 if (result.hasErrors()) {
             return "/productos/add-producto";
         }
         
    	 productoService.save(producto);

 		Constantes constantes=new Constantes();
 		model.addAttribute("listaTipoactivo",constantes.ArregloStringTiposActivo());
 		model.addAttribute("listProducto", productoService.listAll());
         return "/productos/list-producto";
    }
    
    
    @GetMapping("/c-edit-producto/{id}")
    public String showEditarProducto(@PathVariable("id") long id, Model model) {
    	Producto producto = productoService.findById(id);
    	
        model.addAttribute("producto", producto);
        Constantes constantes=new Constantes();
		model.addAttribute("listaTipoactivo",constantes.TiposActivo);
        model.addAttribute("listaCategoria",categoriaService.listAll());
        return "/productos/edit-producto";
    }
    
    
    @PostMapping("/do-edit-producto")
    public String doEditProducto(@Valid Producto producto, BindingResult result, Model model){
    	 if (result.hasErrors()) {
             return "/productos/edit-producto"+"/"+producto.getId();
         }
    	 productoService.save(producto);
      	
  		Constantes constantes=new Constantes();
  		model.addAttribute("listaTipoactivo",constantes.ArregloStringTiposActivo());
  		model.addAttribute("listProducto", productoService.listAll());
          return "/productos/list-producto";
    }

    @GetMapping("/do-delete-producto/{id}")
    public String doEliminarProducto(@PathVariable("id") long id, Model model) {
    	Producto producto = productoService.findById(id);
        productoService.delete(producto);
        
  		Constantes constantes=new Constantes();
  		model.addAttribute("listaTipoactivo",constantes.ArregloStringTiposActivo());
  		model.addAttribute("listProducto", productoService.listAll());
          return "/productos/list-producto";
    }
	
}
