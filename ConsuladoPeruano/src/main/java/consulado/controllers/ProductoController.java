package consulado.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import consulado.entities.Producto;
import consulado.services.CategoriaService;

import consulado.services.ProductoService;
import consulado.utils.Constantes;
import consulado.utils.GestorArchivos;


@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	@Value("${consulado.ruta.imagenes}")
	private String ruta_imagenes;
	
	@Autowired
	private ProductoService productoService;
	@Autowired
	private CategoriaService categoriaService;
	
	@Secured("ROLE_SUPER_ADMIN")
	@GetMapping("/c-list-producto")
    public String showListaProductos(Model model){
    	
		Constantes constantes=new Constantes();
		model.addAttribute("listaTipoactivo",constantes.ArregloString("TiposActivo"));
		model.addAttribute("listProducto", productoService.listAll());
        return "/productos/list-producto";
    }
    
	@Secured("ROLE_SUPER_ADMIN")
    @GetMapping("/c-add-producto")
    public String showNuevoProducto(Model model){
    	
    	Constantes constantes=new Constantes();
		model.addAttribute("producto", new Producto());
		model.addAttribute("listaTipoactivo",constantes.TiposActivo);
		model.addAttribute("listaCategoria",categoriaService.listAll());
        return "/productos/add-producto";
    }
    
	@Secured("ROLE_SUPER_ADMIN")
    @PostMapping("/do-add-producto")
    public String doNuevoProducto(@Valid Producto producto, BindingResult result, Model model){
    	 if (result.hasErrors()) {
             return "/productos/add-producto";
         }
         producto=productoService.save(producto);
    	 model.addAttribute("producto", producto);
         return "/productos/edit-producto-imagen";
    }
    
	@Secured("ROLE_SUPER_ADMIN")
    @GetMapping("/c-edit-producto/{id}")
    public String showEditarProducto(@PathVariable("id") long id, Model model) {
    	Producto producto = productoService.findById(id);
    	
        model.addAttribute("producto", producto);
        Constantes constantes=new Constantes();
		model.addAttribute("listaTipoactivo",constantes.TiposActivo);
        model.addAttribute("listaCategoria",categoriaService.listAll());
        return "/productos/edit-producto";
    }
    
	@Secured("ROLE_SUPER_ADMIN")
    @PostMapping("/do-edit-producto")
    public String doEditProducto(@Valid Producto producto, BindingResult result, Model model){
    	 if (result.hasErrors()) {
             return "/productos/edit-producto"+"/"+producto.getId();
         }
    	 productoService.save(producto);
      	
  		Constantes constantes=new Constantes();
  		model.addAttribute("listaTipoactivo",constantes.ArregloString("TiposActivo"));
  		model.addAttribute("listProducto", productoService.listAll());
          return "/productos/list-producto";
    }

	
	@Secured("ROLE_SUPER_ADMIN")
    @GetMapping("/c-edit-producto-imagen/{id}")
    public String showEditarProductoImagen(@PathVariable("id") long id, Model model) {
    	Producto producto = productoService.findById(id);
    	
        model.addAttribute("producto", producto);
        return "/productos/edit-producto-imagen";
    }
	
	
	@Secured("ROLE_SUPER_ADMIN")
    @PostMapping("/do-edit-producto-imagen")
	public String doEditProductoImagen(Model model, RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart,  @RequestParam("producto_id") Long producto_id) {
		String nombreImagen = null;
		Producto producto=productoService.findById(producto_id);
		
		if (!multiPart.isEmpty()) {
			nombreImagen = GestorArchivos.guardarArchivo(multiPart, ruta_imagenes, producto_id.toString()+"_");
			if (nombreImagen != null){ 
				producto.setImagen(nombreImagen);
				producto=productoService.save(producto);
			}
		}
		
		 model.addAttribute("producto", producto);
	        
        return "/productos/edit-producto-imagen";

	}
	
	
	
	@Secured("ROLE_SUPER_ADMIN")
    @GetMapping("/do-delete-producto/{id}")
    public String doEliminarProducto(@PathVariable("id") long id, Model model) {
    	Producto producto = productoService.findById(id);
        productoService.delete(producto);
        
  		Constantes constantes=new Constantes();
  		model.addAttribute("listaTipoactivo",constantes.ArregloString("TiposActivo"));
  		model.addAttribute("listProducto", productoService.listAll());
          return "/productos/list-producto";
    }
	
}
