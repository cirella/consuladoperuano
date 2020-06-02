package consulado.services;

import java.util.List;

import consulado.entities.Empleado;

public interface EmpleadoService {
	   public Empleado save(Empleado empleado);
	   public Empleado save(Empleado empleado, boolean crea_usuario);
	   public Empleado saveSinFK(Empleado empleado);
	   
	   public void delete(Empleado empleado);
	   public Empleado findById(Long id);

	   public List<Empleado> listAll();

}
