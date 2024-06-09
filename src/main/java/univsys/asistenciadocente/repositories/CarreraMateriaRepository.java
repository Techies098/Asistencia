package univsys.asistenciadocente.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univsys.asistenciadocente.models.CarreraEntity;
import univsys.asistenciadocente.models.CarreraMateriaEntity;

import java.util.List;

@Repository
public interface CarreraMateriaRepository extends CrudRepository<CarreraMateriaEntity,Long> {
    List<CarreraMateriaEntity> findByCarrera(CarreraEntity carrera);
}
