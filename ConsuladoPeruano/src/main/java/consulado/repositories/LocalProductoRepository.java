package consulado.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import consulado.entities.LocalProducto;

@Repository
public interface LocalProductoRepository extends JpaRepository<LocalProducto, Long> {
	@Query("select l from LocalProducto l where l.id=?1")
	List<LocalProducto> findByIdLocalProducto(Long id);
	@Query("select l from LocalProducto l where l.producto.id=?1")
	List<LocalProducto> findByIdProducto(Long id);
	@Query("select l from LocalProducto l where l.local.id=?1 order by l.producto.categoria.nombrecategoria")
	List<LocalProducto> findByIdLocal(Long id);
	@Query("select l from LocalProducto l where l.local.id=?1 and l.producto.id=?2")
	List<LocalProducto> findByIdLocalIdProducto(Long local_id, Long producto_id);
		
}
