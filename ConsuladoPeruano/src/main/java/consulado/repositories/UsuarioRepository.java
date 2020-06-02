package consulado.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import consulado.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	@Query("select u from Usuario u where u.nombreusuario=?1")
	List<Usuario> findByNombreUsuario(String nombreusuario);
	@Query("select u from Usuario u where u.nombreusuario=?1 and u.password=?2")
	List<Usuario> findByNombreUsuarioPassword(String nombreusuario, String password);
	@Query("select u from Usuario u where u.id=?1")
	List<Usuario> findByIdUsuario(Long id);
	
/*	
 * 
 * @Query(value="select u from Usuario u where u.usuario=:usuario")
	List<Usuario> findByUsuario(@Param("usuario") String usuario);
	
 * 
	@Query("select count(p.name) from Product p where p.name =:name")
	public int buscarNombreProducto(@Param("name") String nombreProduct);
	@Query("select u from Usuario u where u.id_usuario=?1")
	List<Usuario> findByIdUsuario(Long idUsuario);*/
}
