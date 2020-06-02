package consulado.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import consulado.entities.Direccion;

@Repository
public interface DireccionRepository  extends JpaRepository<Direccion, Long>{
	@Query("select d from Direccion d where d.latitud = ?1 and d.longitud =?2")
	List<Direccion> findByLatitudLongitud(double latitud, double longitud);
	@Query("select d from Direccion d where d.nombredireccion like ?1%")
	List<Direccion> findByDireccion(String direccion);
}
