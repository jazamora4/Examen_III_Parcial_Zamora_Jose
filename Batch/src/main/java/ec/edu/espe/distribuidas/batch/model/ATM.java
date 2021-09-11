package ec.edu.espe.distribuidas.batch.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "ATM")
public class ATM {
    @Id
    private String id;
    private String nombre;
    private Ubicacion ubicacion;
}
