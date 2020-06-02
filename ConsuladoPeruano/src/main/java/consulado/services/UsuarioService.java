package consulado.services;

import java.util.List;

import consulado.entities.Usuario;

public interface UsuarioService {
	   public Usuario save(Usuario usuario);
	   public void delete(Usuario usuario);
	   public Usuario makeUsuario(String nombreusuario, int tipo);

	   public List<Usuario> listAll();
	   public Usuario findByIdUsuario(Long idUsuario);
	   public Usuario findByNombreUsuario(String nombreusuario); 
	   
	   public Usuario validaUsuarioPassword(String nombreusuario, String password);
}
