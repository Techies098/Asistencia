package univsys.asistenciadocente.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univsys.asistenciadocente.models.AsistenciaEntity;
import univsys.asistenciadocente.models.HorarioEntity;

import java.util.List;

@Repository
public interface AsistenciaRepository extends CrudRepository<AsistenciaEntity,Long> {
    List<AsistenciaEntity> findByHorarioGrupoId(Long grupoId);
}

