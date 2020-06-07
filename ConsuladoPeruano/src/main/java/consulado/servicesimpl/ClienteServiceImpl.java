package consulado.servicesimpl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import consulado.entities.Cliente;

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
			Usuario usuario=usuarioService.makeUsuario(cliente.getEmail(), constantes.findByTipoUsuario("Cliente"));
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

}
