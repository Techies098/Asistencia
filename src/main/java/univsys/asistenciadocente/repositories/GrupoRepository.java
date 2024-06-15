package univsys.asistenciadocente.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univsys.asistenciadocente.models.GrupoEntity;
@Repository
public interface GrupoRepository extends CrudRepository<GrupoEntity,Long> {
}
