package univsys.asistenciadocente.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univsys.asistenciadocente.models.MateriaEntity;

@Repository
public interface MateriaRepository extends CrudRepository<MateriaEntity,Long> {
    MateriaEntity findByname(String name);
}
