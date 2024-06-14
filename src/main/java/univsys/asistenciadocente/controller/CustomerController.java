package univsys.asistenciadocente.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
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
