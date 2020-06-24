package consulado.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import consulado.entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>  {
	@Query("select p from Producto p where p.nombreproducto like ?1%")
	List<Producto> findByNombreProducto(String producto);
	@Query("select p from Producto p where p.id=?1")
	List<Producto> findByIdProducto(Long id);
	@Query("select p from Producto p where p.categoria.id=?1")
	List<Producto> findByCategoriaId(Long id);
	@Query("select p from Producto p where p.categoria.id=?1 and p.activado=?2")
	List<Producto> findByCategoriaIdActivado(Long idCategoria,int activado);
	@Query("select p from Producto p where p.activado=?1 order by p.categoria")
	List<Producto> findByActivado(int activado);
}
