package ec.edu.espe.distribuidas.documental.dao;

import ec.edu.espe.distribuidas.documental.model.Transaccion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransaccionRepository extends MongoRepository<Transaccion, String> {
}
