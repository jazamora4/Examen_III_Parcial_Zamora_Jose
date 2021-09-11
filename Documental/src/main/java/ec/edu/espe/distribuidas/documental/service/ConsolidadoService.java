package ec.edu.espe.distribuidas.documental.service;

import ec.edu.espe.distribuidas.documental.dao.ATMRepository;
import ec.edu.espe.distribuidas.documental.dao.ConsolidadoRepository;
import ec.edu.espe.distribuidas.documental.model.ATM;
import ec.edu.espe.distribuidas.documental.model.Consolidado;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsolidadoService {
    private final ConsolidadoRepository consolidadoRepository;
    private final ATMRepository atmRepository;

    public ConsolidadoService(ConsolidadoRepository consolidadoRepository, ATMRepository atmRepository) {
        this.consolidadoRepository = consolidadoRepository;
        this.atmRepository = atmRepository;
    }

    public Consolidado listConsolidadoByCajeroId(String cajero){
        List<Consolidado> consolidados = this.consolidadoRepository.findByCajero(cajero);
        return consolidados.get(consolidados.size()-1);
    }

    public List<Consolidado> listConsolidadoLessThanValue(BigDecimal monto){
        List<ATM> cajeros = this.atmRepository.findAll();
        List<Consolidado> response = new ArrayList<>();
        for (ATM cajero: cajeros){
            List<Consolidado> consolidados = this
                    .consolidadoRepository
                    .findByCajero(cajero.getId());
            Consolidado consolidado = consolidados.get(consolidados.size()-1);
            if(consolidado.getDisponible().compareTo(monto)==-1){
                response.add(consolidado);
            }
        }

        return response;
    }
}
