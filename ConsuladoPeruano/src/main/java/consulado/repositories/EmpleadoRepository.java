package consulado.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import consulado.entities.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>  {
	@Query("select e from Empleado e where e.id=?1")
	List<Empleado> findByIdEmpleado(Long id);
	
	@Query("select e from Empleado e where e.apellidos like ?1%")
	List<Empleado> findByApellidos(String apellidos);
	@Query("select e from Empleado e where e.tipodocumento = ?1 and e.numerodocumento =?2")
	List<Empleado> findByTipoDocumentoNumeroDocumento(String tipodocumento, String numerodocumento);
}
