package univsys.asistenciadocente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class CustomerController {

    @GetMapping("/index")
    public String index() {
        return "hello world";
    }

    @GetMapping("/index2")
    public String index2() {
        return "Hello world not secured";
    }
}
