package ec.edu.espe.distribuidas.batch.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Consolidado")
public class Consolidado {
    @Id
    private String id;
    private String cajero;
    private BigDecimal disponible;
    private Billetes billetes;
    private Date timestamp;
}
