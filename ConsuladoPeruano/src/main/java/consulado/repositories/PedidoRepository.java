package consulado.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import consulado.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	@Query("select p from Pedido p where p.id=?1")
	List<Pedido> findByIdPedido(Long id);
	@Query("select p from Pedido p where p.cliente.id=?1")
	List<Pedido> findByIdCliente(Long id);
	@Query("select p from Pedido p where p.local.id=?1 and p.cliente.apellidos like ?2% order by p.horapedido DESC")
	List<Pedido> findByIdLocalApellido(Long id,String apellidos);
	@Query("select p from Pedido p where p.cliente.id=?1 and p.cancelado=0 and p.entregado=0")
	List<Pedido> findByIdClientePendiente(Long id);
	@Query("select p from Pedido p where p.cliente.id=?1 and (p.cancelado=1 or p.entregado=1)")
	List<Pedido> findByIdClienteCompletado(Long id);
	@Query("select p from Pedido p where p.local.id=?1 and p.cancelado=0 and p.entregado=0")
	List<Pedido> listByIdLocalPendiente(Long id);
	@Query("select p from Pedido p where p.local.id=?1 and (p.cancelado=1 or p.entregado=1)")
	List<Pedido> listByIdLocalCompletado(Long id);
	@Query("select p from Pedido p where p.local.id=?1 and p.cancelado=0 and p.entregado=0 and p.preparado=0")
	List<Pedido> listByIdLocalRegistrado(Long id);
	@Query("select p from Pedido p where p.local.id=?1 and p.cancelado=0 and p.entregado=0 and p.preparado=1")
	List<Pedido> listByIdLocalPreparado(Long id);
	@Query("select p from Pedido p where p.local.id=?1 and p.cancelado=0 and p.entregado=0 and p.preparado=1 and p.empleado is null")
	List<Pedido> listByIdLocalPreparadoSinAsignar(Long id);
	@Query("select p from Pedido p where p.local.id=?1 and p.cancelado=0 and p.entregado=1 and p.preparado=0")
	List<Pedido> listByIdLocalEntregado(Long id);
	@Query("select p from Pedido p where p.local.id=?1 and p.cancelado=1 and p.entregado=0 and p.preparado=0")
	List<Pedido> listByIdLocalCancelado(Long id);
	@Query("select p from Pedido p where p.empleado.id=?1 and p.cancelado=0 and p.entregado=0 and p.preparado=1")
	List<Pedido> listByIdEmpleadoSinEntregar(Long id);
	
	
	
	
	
	
	
	
}
