package consulado.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import consulado.entities.Categoria;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria, Long>{
	@Query("select c from Categoria c where c.id=?1")
	public List<Categoria> findByIdCategoria(Long id);
}
