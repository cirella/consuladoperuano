package consulado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import consulado.entities.LocalProducto;

@Repository
public interface LocalProductoRepository extends JpaRepository<LocalProducto, Long> {

}
