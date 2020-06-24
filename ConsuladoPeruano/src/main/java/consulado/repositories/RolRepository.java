package consulado.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import consulado.entities.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
	@Query("select r from Rol r where r.id=?1")
	public List<Rol> findByIdRol(Long id);
	@Query("select r from Rol r where r.usuario.id=?1")
	public List<Rol> findByIdUsuario(Long id);
}
