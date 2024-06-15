package univsys.asistenciadocente.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univsys.asistenciadocente.models.AsistenciaEntity;
@Repository
public interface AsistenciaRepository extends CrudRepository<AsistenciaEntity,Long> {
}
