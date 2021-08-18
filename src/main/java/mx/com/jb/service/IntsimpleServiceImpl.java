package mx.com.jb.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import mx.com.jb.dao.IntsimpleDao;
import mx.com.jb.domain.Intsimple;
import mx.com.jb.domain.IntsimpleInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IntsimpleServiceImpl implements IntsimpleService  {
    
    @Autowired
    private IntsimpleDao intsimpleDao;

    @Override
    @Transactional
    public List<Intsimple> calculaService(IntsimpleInput intsimpleInput) {
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

    @Override
    @Transactional(readOnly = true)
    public List<Intsimple> readService() {
        return ((List<Intsimple>) intsimpleDao.findAll());
    }
    
}
