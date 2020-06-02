package consulado.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import consulado.entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>  {
	@Query("select p from Producto p where p.nombreproducto like ?1%")
	List<Producto> findByProducto(String producto);
}
