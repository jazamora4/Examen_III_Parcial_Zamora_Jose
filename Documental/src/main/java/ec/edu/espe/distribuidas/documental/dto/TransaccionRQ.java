package ec.edu.espe.distribuidas.documental.dto;

import ec.edu.espe.distribuidas.documental.model.Billetes;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransaccionRQ {
    private String idCajero;
    private Billetes billetes;
    private BigDecimal monto;
    private String tipo;
}
