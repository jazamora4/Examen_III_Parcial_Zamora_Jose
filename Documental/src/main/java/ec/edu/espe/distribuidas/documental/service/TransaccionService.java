package ec.edu.espe.distribuidas.documental.service;

import ec.edu.espe.distribuidas.documental.dao.TransaccionRepository;
import ec.edu.espe.distribuidas.documental.model.Transaccion;
import java.util.Date;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransaccionService {
    private final TransaccionRepository transaccionRepository;

    public TransaccionService(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    public void insertTransaction(Transaccion transaccion){
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        transaccion.setEstado("NCO");
        transaccion.setTimestamp(new Date());
        this.transaccionRepository.save(transaccion);
    }
}
