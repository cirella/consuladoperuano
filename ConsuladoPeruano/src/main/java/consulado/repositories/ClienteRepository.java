package consulado.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import consulado.entities.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	@Query("select c from Cliente c where c.id=?1")
	List<Cliente> findByIdCliente(Long id);
	@Query("select c from Cliente c where c.apellidos like ?1%")
	List<Cliente> findByApellidos(String apellidos);
	@Query("select c from Cliente c where c.tipodocumento = ?1 and c.numerodocumento =?2")
	List<Cliente> findByTipoDocumentoNumeroDocumento(int tipodocumento, String numerodocumento);
}
