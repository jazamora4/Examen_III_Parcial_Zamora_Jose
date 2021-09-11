package ec.edu.espe.distribuidas.documental.controller;

import ec.edu.espe.distribuidas.documental.model.Consolidado;
import ec.edu.espe.distribuidas.documental.service.ConsolidadoService;
import java.math.BigDecimal;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/v1/consolidado")
public class ConsolidadoController {
    private final ConsolidadoService service;

    public ConsolidadoController(ConsolidadoService service) {
        this.service = service;
    }

    @GetMapping("cajero/{id}")
    public ResponseEntity obtenerConsolidadoPorCajero(@PathVariable("id") String cajero){
        Consolidado response = this.service.listConsolidadoByCajeroId(cajero);
        return ResponseEntity.ok(response);
    }

    @GetMapping("cajeros/{valor}")
    public ResponseEntity obtenerCajerosConMontosMenores(@PathVariable("valor") BigDecimal monto){
        List<Consolidado> response = this.service.listConsolidadoLessThanValue(monto);
        return ResponseEntity.ok(response);
    }
}
