package univsys.asistenciadocente.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univsys.asistenciadocente.models.HorarioEntity;

import java.util.List;

@Repository
public interface HorarioRepository extends CrudRepository<HorarioEntity,Long> {
    List<HorarioEntity> findByAulaId(Long aulaId);
}
