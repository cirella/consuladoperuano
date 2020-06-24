package consulado.services;

import java.util.List;

import consulado.entities.Cliente;

public interface ClienteService {
	   public Cliente save(Cliente cliente);
	   public Cliente save(Cliente cliente, boolean crea_usuario);
	   public Cliente saveSinFK(Cliente cliente);
	   
	   public void delete(Cliente cliente);
	   public Cliente findById(Long id);
	   public Cliente findByTipoNumeroDocumento(int tipo,String numerodocumento);
	   public List<Cliente> findByApellidos(String apellidos);
	   public Cliente findByIdUsuario(Long idusuario);
	   
	   

	   public List<Cliente> listAll();

}
