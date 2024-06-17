package univsys.asistenciadocente.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univsys.asistenciadocente.models.LicenciaEntity;

import java.util.List;

@Repository
public interface LicenciaRepository extends CrudRepository<LicenciaEntity,Long> {
    List<LicenciaEntity> findByUserId(Long userId);
}
