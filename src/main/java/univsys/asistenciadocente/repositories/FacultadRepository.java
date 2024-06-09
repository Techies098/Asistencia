package univsys.asistenciadocente.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univsys.asistenciadocente.models.FacultadEntity;

@Repository
public interface FacultadRepository extends CrudRepository<FacultadEntity,Long> {
}
