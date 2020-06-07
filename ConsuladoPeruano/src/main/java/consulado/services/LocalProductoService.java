package consulado.services;

import java.util.List;

import consulado.entities.Local;
import consulado.entities.LocalProducto;
import consulado.entities.Producto;

public interface LocalProductoService {

	public LocalProducto save(LocalProducto localproducto);
	public void delete(LocalProducto localproducto);
	public void deleteByIdProducto(Long id);
	
	public LocalProducto findById(Long id);
	public List<LocalProducto> findByIdProducto(Long id);
	public List<LocalProducto> findByIdLocal(Long id);

	public void remueveStockIdProducto(Long id);
	
	public List<LocalProducto> findAll();
	
	public int existeProducto(Producto producto, List<LocalProducto> lista);
	public void cargaLocalProductoParaLocal(Local local);
	public void actualizaStockLocal(Local local);

}
