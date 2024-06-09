package univsys.asistenciadocente.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univsys.asistenciadocente.models.CarreraEntity;

@Repository
public interface CarreraRepository extends CrudRepository<CarreraEntity,Long> {
    CarreraEntity findByname(String name);
    CarreraEntity findBySigla(String name);
}
