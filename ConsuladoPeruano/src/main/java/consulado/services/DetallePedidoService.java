package consulado.services;

import java.util.List;

import consulado.entities.DetallePedido;


public interface DetallePedidoService {
	
	public DetallePedido save(DetallePedido detallepedido);
	public void delete(DetallePedido detallepedido);
	public void deleteByIdPedido(Long id);
	
	public DetallePedido findById(Long id);
	public List<DetallePedido> findByIdPedido(Long id);
	public List<DetallePedido> findByIdCategoria(Long id);
	
	public List<DetallePedido> listNuevoPedido();
	
	

}
