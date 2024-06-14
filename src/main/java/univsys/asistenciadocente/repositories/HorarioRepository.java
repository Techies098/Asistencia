package univsys.asistenciadocente.repositories;

import org.springframework.data.repository.CrudRepository;
import univsys.asistenciadocente.models.HorarioEntity;

public interface HorarioRepository extends CrudRepository<HorarioEntity,Long> {
}
