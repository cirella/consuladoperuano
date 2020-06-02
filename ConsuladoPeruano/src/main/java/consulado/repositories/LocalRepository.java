package consulado.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import consulado.entities.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long>  {
	@Query("select l from Local l where l.id=?1")
	public List<Local> findByIdLocal(Long id);
}
