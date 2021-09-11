package ec.edu.espe.distribuidas.documental.controller;

import ec.edu.espe.distribuidas.documental.dto.TransaccionRQ;
import ec.edu.espe.distribuidas.documental.model.Transaccion;
import ec.edu.espe.distribuidas.documental.service.TransaccionService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/v1/transaccion")
public class TransaccionController {
    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @PostMapping
    @ApiOperation(value = "Inserta una transaccion en el cajero")
    public ResponseEntity crearTransaccion(@RequestBody TransaccionRQ request){
        Transaccion transaccion = new Transaccion();
        transaccion.setIdCajero(request.getIdCajero());
        transaccion.setMonto(request.getMonto());
        transaccion.setBilletes(request.getBilletes());
        transaccion.setTipo(request.getTipo());
        transaccionService.insertTransaction(transaccion);
        return ResponseEntity.ok().build();
    }

}
