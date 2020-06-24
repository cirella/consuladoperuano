package consulado.servicesimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import consulado.entities.DetallePedido;
import consulado.entities.Producto;
import consulado.repositories.DetallePedidoRepository;
import consulado.repositories.ProductoRepository;
import consulado.services.DetallePedidoService;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private DetallePedidoRepository detallePedidoRepository;
	@Autowired
	private ProductoRepository productoRepository;

	@Transactional
	@Override
	public DetallePedido save(DetallePedido detallepedido) {
		return detallePedidoRepository.save(detallepedido);
	}

	@Transactional
	@Override
	public void delete(DetallePedido detallepedido) {
		detallePedidoRepository.delete(detallepedido);
	}

	@Override
	public void deleteByIdPedido(Long id) {

	}

	@Override
	public DetallePedido findById(Long id) {
		List<DetallePedido> detallespedidos = detallePedidoRepository.findByIdDetallePedido(id);
		if (detallespedidos.isEmpty()) {
			return null;
		} else {
			return detallespedidos.get(0);
		}
	}

	@Override
	public List<DetallePedido> findByIdPedido(Long id) {
		return detallePedidoRepository.findByIdPedido(id);

	}

	@Override
	public List<DetallePedido> findByIdCategoria(Long id) {
		return detallePedidoRepository.findByIdCategoria(id);
	}

	@Override
	public List<DetallePedido> listNuevoPedido() {
		List<Producto> listProducto=productoRepository.findByActivado(1);
		List<DetallePedido> detallespedidos=new ArrayList<>();
		
		for (Producto producto:listProducto) {
			DetallePedido detallepedido=new DetallePedido();
			detallepedido.setId(0);
			detallepedido.setCantidad(0);
			detallepedido.setPedido(null);
			detallepedido.setSubtotal(0);
			detallepedido.setProducto(producto);
			detallespedidos.add(detallepedido);
		}
		return detallespedidos;
	}

}
