package consulado.servicesimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import consulado.entities.Cliente;
import consulado.entities.Rol;
import consulado.entities.Usuario;
import consulado.repositories.ClienteRepository;

import consulado.services.ClienteService;

import consulado.services.UsuarioService;
import consulado.utils.Constantes;


@Service
public class ClienteServiceImpl implements ClienteService, Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ClienteRepository clienteRepository;	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteRepository.save(cliente);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente, boolean crea_usuario) {
		if (crea_usuario) {
			Constantes constantes = new Constantes();
			
			Usuario usuario=new Usuario();
			usuario.setNombreusuario(cliente.getEmail());
			usuario.setPassword(passwordEncoder.encode(cliente.getNumerodocumento()));
			usuario.setTipo(constantes.findByTipo("TiposUsuario","Cliente"));
			
			
			Rol nuevo_rol=new Rol();
			nuevo_rol.setRol(constantes.findById("TiposRol",constantes.findByTipo("TiposUsuario","Cliente")));
			List<Rol> nuevos_roles=new ArrayList<Rol>();
			nuevos_roles.add(nuevo_rol);
			usuario.setRoles(nuevos_roles);
			usuario=usuarioService.save(usuario);
			
			cliente.setUsuario(usuario);
			this.save(cliente);
		} 
		return this.save(cliente);
	}

	@Override
	@Transactional
	public Cliente saveSinFK(Cliente cliente) {
		Usuario usuario=usuarioService.findByIdUsuario(findById(cliente.getId()).getUsuario().getId());
		cliente.setUsuario(usuario);
		return this.save(cliente);
	}

	@Override
	public void delete(Cliente cliente) {
		usuarioService.delete(cliente.getUsuario());
		clienteRepository.delete(cliente);
	}

	@Override
	public Cliente findById(Long id) {
		List<Cliente> clientes=clienteRepository.findByIdCliente(id);
		if (clientes.isEmpty()) {
			return null;
		} else {
			return clientes.get(0);
		}
	}

	@Override
	public Cliente findByTipoNumeroDocumento(int tipo, String numerodocumento) {
		List<Cliente> clientes=clienteRepository.findByTipoDocumentoNumeroDocumento(tipo,numerodocumento);
		if (clientes.isEmpty()) {
			return null;
		} else {
			return clientes.get(0);
		}
	}

	@Override
	public List<Cliente> findByApellidos(String apellidos) {
		List<Cliente> clientes=clienteRepository.findByApellidos(apellidos);
		return clientes;
	}

	@Override
	public List<Cliente> listAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>)clienteRepository.findAll();
	}

	@Override
	public Cliente findByIdUsuario(Long idusuario) {
		List<Cliente> clientes=clienteRepository.findByIdUsuario(idusuario);
		if (clientes.isEmpty()) {
			return null;
		} else {
			return clientes.get(0);
		}
	}

}
