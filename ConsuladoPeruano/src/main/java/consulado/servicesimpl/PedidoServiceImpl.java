package consulado.servicesimpl;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import consulado.entities.DetallePedido;
import consulado.entities.Empleado;
import consulado.entities.LocalProducto;
import consulado.entities.Pedido;
import consulado.entities.Producto;
import consulado.repositories.PedidoRepository;

import consulado.services.ClienteService;
import consulado.services.DetallePedidoService;
import consulado.services.DireccionService;

import consulado.services.EmpleadoService;
import consulado.services.LocalProductoService;
import consulado.services.PedidoService;
import consulado.services.ProductoService;
import consulado.utils.Constantes;

@Service
public class PedidoServiceImpl implements PedidoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private DireccionService direccionService;
	@Autowired
	private ProductoService productoService;	
	@Autowired
	private DetallePedidoService detallepedidoService;
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private LocalProductoService localproductoService;
	
	@Transactional
	@Override
	public Pedido save(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	@Transactional
	@Override
	public Pedido saveSinFK(Pedido pedido) {
		Pedido pedido_original=findById(pedido.getId());
		pedido.setCancelado(pedido_original.getCancelado());
		pedido.setEntregado(pedido_original.getEntregado());
		pedido.setHoracancelado(pedido_original.getHoracancelado());
		pedido.setHoraentregado(pedido_original.getHoraentregado());
		pedido.setHorapedido(pedido_original.getHorapedido());
		pedido.setHorapreparado(pedido_original.getHorapreparado());
		pedido.setIdempleadodelivery(pedido_original.getIdempleadodelivery());
		pedido.setIgv(pedido_original.getIgv());
		pedido.setObservacion(pedido_original.getObservacion());		
		pedido.setPrecioventa(pedido_original.getPrecioventa());
		pedido.setPreparado(pedido_original.getPreparado());
		pedido.setValorventa(pedido_original.getValorventa());
		return save(pedido);
	}

	@Transactional
	@Override
	public void delete(Pedido pedido) {
		pedidoRepository.delete(pedido);
	}

	@Override
	public Pedido findById(Long id) {
		List<Pedido> pedidos=pedidoRepository.findByIdPedido(id);
		if (pedidos.isEmpty()) {
			return null;
		} else {
			return pedidos.get(0);
		}
	}

	@Override
	public List<Pedido> listAll() {
		return (List<Pedido>)pedidoRepository.findAll();
	}

	@Override
	public List<Pedido> findByIdCliente(Long id) {
		return (List<Pedido>)pedidoRepository.findByIdCliente(id);
	}

	@Override
	public List<Pedido> findByIdLocalApellido(Long id, String apellidos) {
		return (List<Pedido>)pedidoRepository.findByIdLocalApellido(id, apellidos);
	}

	@Override
	public List<Pedido> findByIdClientePendiente(Long id) {
		return (List<Pedido>)pedidoRepository.findByIdClientePendiente(id);
	}

	@Override
	public List<Pedido> findByIdClienteCompletado(Long id) {
		return (List<Pedido>)pedidoRepository.findByIdClienteCompletado(id);
	}

	@Override
	public List<Pedido> listByIdLocalPendiente(Long id) {
		return (List<Pedido>)pedidoRepository.listByIdLocalPendiente(id);
	}

	@Override
	public List<Pedido> listByIdLocalCompletado(Long id) {
		return (List<Pedido>)pedidoRepository.listByIdLocalCompletado(id);
	}

	@Override
	public List<Pedido> listByIdLocalRegistrado(Long id) {
		return (List<Pedido>)pedidoRepository.listByIdLocalRegistrado(id);
	}

	@Override
	public List<Pedido> listByIdLocalPreparado(Long id) {
		return (List<Pedido>)pedidoRepository.listByIdLocalPreparado(id);
	}

	@Override
	public List<Pedido> listByIdLocalEntregado(Long id) {
		return (List<Pedido>)pedidoRepository.listByIdLocalEntregado(id);
	}

	@Override
	public List<Pedido> listByIdLocalCancelado(Long id) {
		return (List<Pedido>)pedidoRepository.listByIdLocalCancelado(id);
	}

	@Transactional
	@Override
	public void makeNuevoPedido(Long idCliente, Long idDireccion, String observacion,  double cuentaTotal,List<DetallePedido> detallespedidos) {
		Pedido pedido=new Pedido();
		pedido.setCancelado(0);
		pedido.setCliente(clienteService.findById(idCliente));
		pedido.setDireccion(direccionService.findById(idDireccion));
		pedido.setEntregado(0);
		pedido.setHorapedido(new Date());
		pedido.setLocal((pedido.getDireccion().getDistrito().getLocal()));
		pedido.setObservacion(observacion);
		pedido.setPreparado(0);
		pedido.setPrecioventa(cuentaTotal);
		pedido.setValorventa(cuentaTotal/1.19);
		pedido.setIgv(pedido.getPrecioventa()-pedido.getValorventa());
		pedido=save(pedido);
		for (DetallePedido detallepedido:detallespedidos) {
			detallepedido.setPedido(pedido);
			detallepedido=detallepedidoService.save(detallepedido);
			LocalProducto localproducto = localproductoService.findByIdLocalIdProducto(pedido.getLocal().getId(),detallepedido.getProducto().getId());
			localproducto.setStock(localproducto.getStock()-detallepedido.getCantidad());
			localproductoService.save(localproducto);
		}
		
	}

	@Override
	public List<Pedido> listByIdEmpleadoSinEntregar(Long id) {
		return (List<Pedido>)pedidoRepository.listByIdEmpleadoSinEntregar(id);
	}

	@Transactional
	@Override
	public void avanzarPreparado(Long idPedido) {
		Pedido pedido=findById(idPedido);
		pedido.setHorapreparado(new Date());
		pedido.setPreparado(1);
		
		Constantes constantes= new Constantes();
		List<Empleado> empleados=empleadoService.listByIdLocalIdTipo(pedido.getLocal().getId(), constantes.findByTipo("TiposEmpleado", "Delivery"));
		int random_pos=(int)(Math.random() * empleados.size());
		
		
		pedido.setEmpleado(empleados.get(random_pos));
		pedido.setIdempleadodelivery((int)(pedido.getEmpleado().getId()));
		save(pedido);
	}

	@Transactional
	@Override
	public void avanzarAsignado(Long idPedido, Long idDelivery) {
	
	}

	@Override
	public List<Pedido> listByIdLocalPreparadoSinAsignar(Long id) {
		return (List<Pedido>)pedidoRepository.listByIdLocalPreparadoSinAsignar(id);
	}
	
	@Transactional
	@Override
	public void avanzarEntregado(Long idPedido) {
		Pedido pedido=findById(idPedido);
		pedido.setHoraentregado(new Date());
		pedido.setPreparado(0);
		pedido.setEntregado(1);
		save(pedido);
		
	}

	@Transactional	
	@Override
	public void avanzarCancelado(Long idPedido, String motivo) {
		Pedido pedido=findById(idPedido);
		pedido.setHoracancelado(new Date());
		pedido.setPreparado(0);
		pedido.setCancelado(1);
		pedido.setObservacion("Observaciones Cliente: \n\r"+pedido.getObservacion()+"\n\r Motivo Cancelación: \n\r"+motivo);
		save(pedido);
		List<DetallePedido> detallespedidos=detallepedidoService.findByIdPedido(pedido.getId());
		for (DetallePedido detallepedido:detallespedidos) {
			LocalProducto localproducto = localproductoService.findByIdLocalIdProducto(pedido.getLocal().getId(),detallepedido.getProducto().getId());
			localproducto.setStock(localproducto.getStock()+detallepedido.getCantidad());
			localproductoService.save(localproducto);
		}
	}

	@Override
	public int[] cantidadesProductoEnCarrito(List<Producto> productos,List<DetallePedido> carrito) {
		int[] cantidades = new int[productos.size()];
		int j = 0;
		for (Producto producto : productos) {
			cantidades[j] = 0;
			if (carrito != null) {
				for (DetallePedido detallepedido : carrito) {
					if (detallepedido.getProducto().getId() == producto.getId()) {
						cantidades[j] = detallepedido.getCantidad();
						break;
					}
				}
			}
			j++;
		}
		return cantidades;
	}

	@Override
	public int cantidadTotalProductosEnCarrito(List<DetallePedido> carrito) {
		int cantidad = 0;
		for (DetallePedido detallepedido : carrito) {
			cantidad=cantidad+detallepedido.getCantidad();
		}
		return cantidad;
	}

	@Override
	public double precioTotalProductosEnCarrito(List<DetallePedido> carrito) {
		double total = 0.0;
		for (DetallePedido detallepedido : carrito) {
			total=total+detallepedido.getSubtotal();
		}
		return total;
	}

	@Override
	public List<DetallePedido> actualizaEnCarrito(List<DetallePedido> carrito, Long producto_id, int cantidad) {
		int posicion = 0;
		for (DetallePedido detallepedido : carrito) {
			if (detallepedido.getProducto().getId() == producto_id) {
				break;
			}
			posicion++;
		}

		if (posicion < carrito.size()) {
			if (cantidad>0) {
				carrito.get(posicion).setCantidad(cantidad);
				carrito.get(posicion).setSubtotal(cantidad * carrito.get(posicion).getProducto().getPrecio());
			} else {
				carrito.remove(posicion);
			}

		} else {
			Producto producto = productoService.findById(producto_id);
			DetallePedido detallepedido = new DetallePedido(0, null, producto, cantidad, producto.getPrecio()*cantidad);
			carrito.add(detallepedido);// adiciona producto seleccionado al carrito

		}
		return carrito;
	}

	@Override
	public int[] stocksProductoEnTienda(List<Producto> productos, Long idLocal) {
		int[] stocks = new int[productos.size()];
		List<LocalProducto> localesproductos=localproductoService.findByIdLocal(idLocal);
		
		int j = 0;
		for (Producto producto : productos) {
			stocks[j] = 0;
			if (localesproductos != null) {
				for (LocalProducto localproducto : localesproductos) {
					if (localproducto.getProducto().getId() == producto.getId()) {
						stocks[j] = localproducto.getStock();
						break;
					}
				}
			}
			j++;
		}
		return stocks;
	}

	
	@Override
	public int[] stocksRecalculadoPorCarrito(List<Producto> productos, List<DetallePedido> carrito, int[] stocks) {
		
		int j = 0;
		for (Producto producto : productos) {
			for (DetallePedido detallepedido : carrito) {
				if (detallepedido.getProducto().getId()==producto.getId()) {
					stocks[j]=stocks[j]-detallepedido.getCantidad();
				}
			}
			j++;
		}
		
		return stocks;
	}
}
