package univsys.asistenciadocente.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univsys.asistenciadocente.models.RoleEntity;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity,Long> {
}
