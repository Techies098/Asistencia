package univsys.asistenciadocente.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univsys.asistenciadocente.models.LicenciaEntity;

@Repository
public interface LicenciaRepository extends CrudRepository<LicenciaEntity,Long> {
}