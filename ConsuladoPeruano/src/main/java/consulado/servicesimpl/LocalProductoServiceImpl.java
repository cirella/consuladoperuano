package consulado.servicesimpl;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import consulado.entities.Local;
import consulado.entities.LocalProducto;
import consulado.entities.Producto;
import consulado.repositories.LocalProductoRepository;
import consulado.services.LocalProductoService;
import consulado.services.LocalService;
import consulado.services.ProductoService;

@Service
public class LocalProductoServiceImpl implements LocalProductoService, Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private LocalProductoRepository localproductoRepository;
	@Autowired
	private ProductoService productoService;
	@Autowired
	private LocalService localService;

	@Override
	@Transactional
	public LocalProducto save(LocalProducto localproducto) {
		// TODO Auto-generated method stub
		return localproductoRepository.save(localproducto);
	}

	@Override
	@Transactional
	public void delete(LocalProducto localproducto) {
		// TODO Auto-generated method stub
		localproductoRepository.delete(localproducto);
	}

	@Override
	public LocalProducto findById(Long id) {
		List<LocalProducto> localesproductos = localproductoRepository.findByIdLocalProducto(id);
		if (localesproductos.isEmpty()) {
			return null;
		} else {
			return localesproductos.get(0);
		}
	}

	@Override
	public List<LocalProducto> findByIdProducto(Long id) {
		return localproductoRepository.findByIdProducto(id);
	}

	@Override
	public List<LocalProducto> findByIdLocal(Long id) {
		List<LocalProducto> resultado=localproductoRepository.findByIdLocal(id);
		resultado.sort(Comparator.comparing(LocalProducto::getProductoNombreCategoria));
		return (List<LocalProducto>) resultado;
	}

	@Override
	public List<LocalProducto> findAll() {
		List<LocalProducto> resultado=localproductoRepository.findAll();
		resultado.sort(Comparator.comparing(LocalProducto::getProductoNombreCategoria));
		return (List<LocalProducto>) resultado;
	}

	@Override
	@Transactional
	public void remueveStockIdProducto(Long id) {
		List<LocalProducto> localesproductos = localproductoRepository.findByIdProducto(id);
		for (LocalProducto localproducto : localesproductos) {
			localproducto.setStock(0);
			save(localproducto);
		}

	}

	@Override
	@Transactional
	public void cargaLocalProductoParaLocal(Local local) {
		List<Producto> productos = productoService.listAll();
		List<LocalProducto> localesproductos = localproductoRepository.findByIdLocal(local.getId());
		if (productos.size() > localesproductos.size()) {
			for (Producto producto : productos) {
				if (producto.getActivado()==1) {
					int index = existeProducto(producto, localesproductos);
					if (index >= 0) {
						localesproductos.remove(index);
					} else {
						LocalProducto localproducto = new LocalProducto();
						localproducto.setLocal(local);
						localproducto.setStock(0);
						localproducto.setProducto(producto);
						save(localproducto);
					}
				}
			}
		}
	}

	@Override
	public int existeProducto(Producto producto, List<LocalProducto> lista) {
		int i = 0;
		for (LocalProducto localproducto : lista) {
			if (localproducto.getProducto().getId() == producto.getId()) {
				break;
			}
			i++;
		}
		return (i == lista.size()) ? -1 : i;
	}

	@Override
	@Transactional
	public void deleteByIdProducto(Long id) {
		// TODO Auto-generated method stub
		List<LocalProducto> localesproductos = findByIdProducto(id);
		for(LocalProducto localproducto: localesproductos) {
			delete(localproducto);
		}
	}

	@Override
	@Transactional
	public void actualizaStockLocal(Local local) {
		Local local_a_guardar=localService.findById(local.getId());
		for(LocalProducto localproducto: local.getLocalesproductos()) {
			localproducto.setProducto(productoService.findById(localproducto.getProducto().getId()));
			localproducto.setLocal(local_a_guardar);
			//System.out.print(local_a_guardar.toString()+"\n\r");
			save(localproducto);
		}
	}

}
