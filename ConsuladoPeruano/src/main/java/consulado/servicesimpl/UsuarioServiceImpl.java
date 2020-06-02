package consulado.servicesimpl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import consulado.entities.Usuario;
import consulado.repositories.UsuarioRepository;
import consulado.services.UsuarioService;
import consulado.utils.PasswordGenerator;

@Service
public class UsuarioServiceImpl implements UsuarioService, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional
	public void delete(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}

	@Override
	public List<Usuario> listAll() {
		return (List<Usuario>)usuarioRepository.findAll();
	}

/*	@Override
	public Usuario findByIdUsuario(Long idUsuario) {
		List<Usuario> usuarios=usuarioRepository.findByIdUsuario(idUsuario);
		if (usuarios==null) {
			return null;
		} else {
			return usuarios.get(0);
		}
	}*/

	@Override
	public Usuario findByNombreUsuario(String nombreusuario) {
		List<Usuario> usuarios=usuarioRepository.findByNombreUsuario(nombreusuario);
		if (usuarios.isEmpty()) {
			return null;
		} else {
			return usuarios.get(0);
		}
	}

	@Override
	public Usuario validaUsuarioPassword(String nombreusuario, String password) {
		List<Usuario> usuarios=usuarioRepository.findByNombreUsuarioPassword(nombreusuario, password);
		if (usuarios.isEmpty()) {
			return null;
		} else {
			Usuario usuario=usuarios.get(0);
			return usuario;
		}
	}

	@Override
	public Usuario makeUsuario(String nombreusuario, int tipo) {
		Usuario usuario=new Usuario();
		usuario.setNombreusuario(nombreusuario);
		usuario.setTipo(tipo);
		
		PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
		        .useDigits(true)
		        .useLower(true)
		        .useUpper(true)
		        .build();
		
		usuario.setPassword(passwordGenerator.generate(8));
		return usuario;
	}

	@Override
	public Usuario findByIdUsuario(Long idUsuario) {
		List<Usuario> usuarios=usuarioRepository.findByIdUsuario(idUsuario);
		if (usuarios.isEmpty()) {
			return null;
		} else {
			Usuario usuario=usuarios.get(0);
			return usuario;
		}
	}

}
