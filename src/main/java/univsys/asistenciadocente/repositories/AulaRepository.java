package univsys.asistenciadocente.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univsys.asistenciadocente.models.AulaEntity;
import java.util.List;

@Repository
public interface AulaRepository extends CrudRepository<AulaEntity,Long> {
    AulaEntity findBynumero(int nro);
    List<AulaEntity> findByModuloId(Long moduloId);
}
