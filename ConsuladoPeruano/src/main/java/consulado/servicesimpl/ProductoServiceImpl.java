package consulado.servicesimpl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import consulado.entities.Producto;
import consulado.repositories.ProductoRepository;
import consulado.services.LocalProductoService;
import consulado.services.ProductoService;
import consulado.utils.Constantes;

@Service
public class ProductoServiceImpl  implements ProductoService, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private LocalProductoService localproductoService;
	
	@Override
	@Transactional
	public Producto save(Producto producto) {
		Producto prod=productoRepository.save(producto);
		Constantes constantes=new Constantes();
		
		if (producto.getActivado()==(constantes.findByTipo("TiposActivo", "Inactivo"))) {
			localproductoService.deleteByIdProducto(producto.getId());
		}
		return prod;
	}

	@Override
	@Transactional
	public void delete(Producto producto) {
		localproductoService.deleteByIdProducto(producto.getId());
		productoRepository.delete(producto);		
	}

	@Override
	public Producto findById(Long id) {

		List<Producto> productos=productoRepository.findByIdProducto(id);
		if (productos.isEmpty()) {
			return null;
		} else {
			return productos.get(0);
		}
	}

	@Override
	public List<Producto> findByCategoriaId(Long id) {
		return (List<Producto>)productoRepository.findByCategoriaId(id);
	}

	@Override
	public List<Producto> findByNombreProducto(String nombreproducto) {
		return (List<Producto>)productoRepository.findByNombreProducto(nombreproducto);
	}

	@Override
	public List<Producto> listAll() {
		return (List<Producto>)productoRepository.findAll();
	}

	@Override
	public List<Producto> findByActivado(int activado) {
		return (List<Producto>)productoRepository.findByActivado(activado);
	}

	@Override
	public List<Producto> findByCategoriaIdActivado(Long idCategoria, int activado) {
		return (List<Producto>)productoRepository.findByCategoriaIdActivado(idCategoria, activado);
	}

}
