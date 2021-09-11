package ec.edu.espe.distribuidas.documental.dao;

import ec.edu.espe.distribuidas.documental.model.ATM;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ATMRepository extends MongoRepository<ATM, String> {
}
