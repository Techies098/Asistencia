package univsys.asistenciadocente.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univsys.asistenciadocente.models.ModuloEntity;
@Repository
public interface ModuloRepository extends CrudRepository<ModuloEntity,Long> {
}
