package univsys.asistenciadocente.repositories;

import org.springframework.data.repository.CrudRepository;
import univsys.asistenciadocente.models.MateriaEntity;

public interface MateriaRepository extends CrudRepository<MateriaEntity,Long> {
}
