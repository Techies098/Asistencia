package univsys.asistenciadocente.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univsys.asistenciadocente.models.CarreraMateriaEntity;
@Repository
public interface CarreraMateriaRepository extends CrudRepository<CarreraMateriaEntity,Long> {
}
