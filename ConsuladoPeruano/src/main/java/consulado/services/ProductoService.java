package consulado.services;

import java.util.List;

import consulado.entities.Producto;

public interface ProductoService {
	   public Producto save(Producto producto);
	   public void delete(Producto producto);
	   public Producto findById(Long id);
	   public List<Producto> findByCategoriaId(Long id);
	   public List<Producto> findByNombreProducto(String nombreproducto);	   
	   public List<Producto> listAll();
	   public List<Producto> findByActivado(int activado);
	   public List<Producto> findByCategoriaIdActivado(Long idCategoria,int activado);
}
