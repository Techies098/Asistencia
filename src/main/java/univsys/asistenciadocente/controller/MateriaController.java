package univsys.asistenciadocente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univsys.asistenciadocente.repositories.CarreraRepository;

@RestController
@RequestMapping("/api/materia")
public class MateriaController {
    @Autowired
    private CarreraRepository carreraRepository;
}
