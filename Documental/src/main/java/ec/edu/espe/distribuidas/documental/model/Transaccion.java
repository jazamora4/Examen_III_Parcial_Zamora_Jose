package ec.edu.espe.distribuidas.documental.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Transaccion")
public class Transaccion {
    @Id
    private String id;
    private String idCajero;
    private BigDecimal monto;
    private Billetes billetes;
    private Date timestamp;
    private String estado;
    private String tipo;

}
