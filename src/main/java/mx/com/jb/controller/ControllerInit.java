package mx.com.jb.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import mx.com.jb.dao.IntsimpleDao;
import mx.com.jb.domain.Intsimple;
import mx.com.jb.domain.IntsimpleInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerInit {
    
    @Autowired
    private IntsimpleDao intsimpleDao;

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String prueba() {
        return "Services for Aplazo";
    }

    @GetMapping(value = "/intsimpleread", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Intsimple> inicio() {
        List<Intsimple> listIntSimple;
        //--
        listIntSimple = (List<Intsimple>) intsimpleDao.findAll();
        return listIntSimple;
    }

    @PostMapping(value = "/intsimplecal", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Intsimple> calcula(@RequestBody IntsimpleInput intsimpleInput) {
        //Variables
        List<Intsimple> listIntSimple = new ArrayList();
        Intsimple tmpIntSimple;
        LocalDate tmpFecha;
        Double dCapital;
        Double dCapAux;
        Double dIntMensual;
        Integer iWeeks;
        //Asignacion valores de entrada
        dCapital = intsimpleInput.getAmount();
        dIntMensual = intsimpleInput.getRate();
        iWeeks = intsimpleInput.getTerms();
        //Calculo capital en interes simple
        dCapAux = ((dCapital*(dIntMensual/100))/(30))*7.5;
        dCapAux = (dCapAux*iWeeks);
        dCapAux = (dCapAux+dCapital);
        dCapAux = (dCapAux/iWeeks);
        //-
        for(int i=1;i<=iWeeks;i++){
            tmpIntSimple = new Intsimple();
            tmpIntSimple.setPayment_number(i);
            tmpIntSimple.setAmount(dCapAux);
            tmpFecha = LocalDate.now().plusWeeks(i);
            tmpIntSimple.setPayment_date(tmpFecha.toString());
            listIntSimple.add(tmpIntSimple); 
        }
        intsimpleDao.saveAll(listIntSimple);
        return listIntSimple;
    }
}
