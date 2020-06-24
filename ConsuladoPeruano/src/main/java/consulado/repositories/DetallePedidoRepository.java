package consulado.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import consulado.entities.DetallePedido;


@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
	@Query("select dp from DetallePedido dp where dp.id=?1")
	List<DetallePedido> findByIdDetallePedido(Long id);
	@Query("select l from LocalProducto l where l.producto.id=?1")
	List<DetallePedido> findByIdPedido(Long id);
	@Query("select l from LocalProducto l where l.producto.categoria.id=?1")
	List<DetallePedido> findByIdCategoria(Long id);
	
}
