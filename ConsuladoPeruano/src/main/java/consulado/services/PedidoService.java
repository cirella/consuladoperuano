package consulado.services;

import java.util.List;

import consulado.entities.DetallePedido;
import consulado.entities.Pedido;
import consulado.entities.Producto;


public interface PedidoService {
	   public Pedido save(Pedido pedido);
	   public Pedido saveSinFK(Pedido pedido);
	   
	   public void delete(Pedido pedido);
	   public Pedido findById(Long id);
	   public List<Pedido> listAll();
	   

	   public List<Pedido> findByIdCliente(Long id);
	   public List<Pedido> findByIdLocalApellido(Long id,String apellidos);
	   public List<Pedido> findByIdClientePendiente(Long id);
	   public List<Pedido> findByIdClienteCompletado(Long id);
	   public List<Pedido> listByIdLocalPendiente(Long id);
	   public List<Pedido> listByIdLocalCompletado(Long id);
	   public List<Pedido> listByIdLocalRegistrado(Long id);
	   public List<Pedido> listByIdLocalPreparado(Long id);
	   public List<Pedido> listByIdLocalPreparadoSinAsignar(Long id);
	   public List<Pedido> listByIdLocalEntregado(Long id);
	   public List<Pedido> listByIdLocalCancelado(Long id);  
	   public List<Pedido> listByIdEmpleadoSinEntregar(Long id);
	   
	   public void makeNuevoPedido(Long idCliente, Long idDireccion, String observacion, double cuentaTotal, List<DetallePedido> detallespedidos);
	   
	   public void avanzarPreparado(Long idPedido);
	   public void avanzarEntregado(Long idPedido);
	   public void avanzarAsignado(Long idPedido, Long idDelivery);	   
	   public void avanzarCancelado(Long idPedido, String motivo);	   	 
	   
	   
	   
	   public int[] stocksProductoEnTienda(List<Producto> productos,Long idLocal);
	   public int[] cantidadesProductoEnCarrito(List<Producto> productos,List<DetallePedido> carrito);
	   public int[] stocksRecalculadoPorCarrito(List<Producto> productos, List<DetallePedido> carrito, int[] stocks);
	   public int cantidadTotalProductosEnCarrito(List<DetallePedido> carrito);
	   public double precioTotalProductosEnCarrito(List<DetallePedido> carrito);
	   public List<DetallePedido> actualizaEnCarrito(List<DetallePedido> carrito, Long producto_id, int cantidad);
	   
	   
	   
}
