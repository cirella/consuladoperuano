package consulado.servicesimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import consulado.entities.Rol;
import consulado.entities.Usuario;

import consulado.repositories.UsuarioRepository;
import consulado.services.UsuarioService;


@Service
public class UsuarioServiceImpl implements UsuarioService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
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
	public Usuario findByIdUsuario(Long idUsuario) {
		List<Usuario> usuarios=usuarioRepository.findByIdUsuario(idUsuario);
		if (usuarios.isEmpty()) {
			return null;
		} else {
			Usuario usuario=usuarios.get(0);
			return usuario;
		}
	}

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = findByNombreUsuario(username);

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Rol rol : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(rol.getRol()));
		}

		System.out.println(user.toString());
		return new User(user.getNombreusuario(), user.getPassword(), true, true, true, true, authorities);
	}
	
	@Transactional
	@Override
	public void cambiaPassword(Long id, String password) {
		Usuario usuario=findByIdUsuario(id);
		String bcryptPassword = passwordEncoder.encode(password);
		usuario.setPassword(bcryptPassword);
		save(usuario);
		
	}
/*
	@Override
	public void creaRol(Long id_usuario, int id_rol) {
		// TODO Auto-generated method stub
		Usuario usuario=findByIdUsuario(id_usuario);
		Rol nuevo_rol=new Rol();
		Constantes constantes=new Constantes();
		nuevo_rol.setRol(constantes.findById("TiposRol",id_rol));
		//List<MyType> myList = new ArrayList<MyType>();
		List<Rol> nuevos_roles=new ArrayList<Rol>();
		nuevos_roles.add(nuevo_rol);
		usuario.setRoles(nuevos_roles);
		save(usuario);
	}
*/
}
