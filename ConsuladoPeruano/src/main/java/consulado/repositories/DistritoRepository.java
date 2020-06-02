package consulado.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import consulado.entities.Distrito;


@Repository
public interface DistritoRepository  extends JpaRepository<Distrito, Long> {
	@Query("select d from Distrito d where d.id=?1")
	List<Distrito> findByIdDistrito(Long id);
	@Query("select d from Distrito d where d.local.id=?1")
	List<Distrito> findByLocalId(Long id);
	@Query("select d from Distrito d where d.local is null")
	List<Distrito> findByLocalIdNull();
}
