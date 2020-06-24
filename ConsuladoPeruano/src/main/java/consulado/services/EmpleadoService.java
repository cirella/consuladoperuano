package consulado.services;

import java.util.List;

import consulado.entities.Empleado;

public interface EmpleadoService {
	   public Empleado save(Empleado empleado);
	   public Empleado save(Empleado empleado, boolean crea_usuario);
	   public Empleado saveSinFK(Empleado empleado);
	   
	   public void delete(Empleado empleado);
	   public Empleado findById(Long id);
	   public void cambiaPassword(Empleado empleado, String nuevo_password);
	   public Empleado findByIdUsuario(Long id);
	   public List<Empleado> listAll();
	   public List<Empleado> listByIdLocal(Long idLocal);
	   public List<Empleado> listByIdLocalIdTipo(Long idLocal, int idTipo);	   

}
