package consulado.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import consulado.entities.Usuario;

public interface UsuarioService extends  UserDetailsService {
	   public Usuario save(Usuario usuario);
	   public void delete(Usuario usuario);
	   
	   public void cambiaPassword(Long id, String password);
	 //  public void creaRol(Long id_usuario, int id_rol);

	   public List<Usuario> listAll();
	   public Usuario findByIdUsuario(Long idUsuario);
	   public Usuario findByNombreUsuario(String nombreusuario); 
	   
	   public Usuario validaUsuarioPassword(String nombreusuario, String password);
}
