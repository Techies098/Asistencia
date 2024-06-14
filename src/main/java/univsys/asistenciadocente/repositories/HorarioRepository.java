package univsys.asistenciadocente.repositories;

import org.springframework.data.repository.CrudRepository;
import univsys.asistenciadocente.models.HorarioEntity;

import java.util.List;

public interface HorarioRepository extends CrudRepository<HorarioEntity,Long> {
    List<HorarioEntity> findByAulaId(Long aulaId);
}
