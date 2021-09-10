package ec.edu.espe.distribuidas.batch.config;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ToString
@Getter
public class ApplicationValues {

    private final String mongoHost;
    private final String mongoDB;
    private final String mongoAut;
    private final String mongoUsr;
    private final String mongoPwd;

    @Autowired
    public ApplicationValues(@Value("${pruebaBatch.mongo.host}") String mongoHost,
                             @Value("${pruebaBatch.mongo.db}") String mongoDB,
                             @Value("${pruebaBatch.mongo.auth}") String mongoAut,
                             @Value("${pruebaBatch.mongo.usr}") String mongoUsr,
                             @Value("${pruebaBatch.mongo.pwd}") String mongoPwd) {
        this.mongoHost = mongoHost;
        this.mongoDB = mongoDB;
        this.mongoAut = mongoAut;
        this.mongoUsr = mongoUsr;
        this.mongoPwd = mongoPwd;
        log.info("Propiedades cargadas: "+this);
    }
}
