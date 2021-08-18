package mx.com.jb.service;

import java.util.List;
import mx.com.jb.domain.Intsimple;
import mx.com.jb.domain.IntsimpleInput;

public interface IntsimpleService {
    
    public List<Intsimple> calculaService(IntsimpleInput intsimpleInput);
    
    public List<Intsimple> readService();
    
}
