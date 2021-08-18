package mx.com.jb.controller;

import java.util.List;
import mx.com.jb.domain.Intsimple;
import mx.com.jb.domain.IntsimpleInput;
import mx.com.jb.service.IntsimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerInit {    
    
    @Autowired
    private IntsimpleService intSimpleService;

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String prueba() {
        return "Services for Aplazo!";
    }

    @GetMapping(value = "/intsimpleread", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Intsimple> inicio() {
        return intSimpleService.readService();
    }

    @PostMapping(value = "/intsimplecal", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Intsimple> calcula(@RequestBody IntsimpleInput intsimpleInput) {
        return intSimpleService.calculaService(intsimpleInput);
    }
}
