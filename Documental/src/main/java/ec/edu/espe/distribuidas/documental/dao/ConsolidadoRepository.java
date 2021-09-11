package ec.edu.espe.distribuidas.documental.dao;

import ec.edu.espe.distribuidas.documental.model.Consolidado;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsolidadoRepository extends MongoRepository<Consolidado, String> {
    public List<Consolidado> findByCajero(String cajero);
    public List<Consolidado> findByDisponibleLessThanAndCajero(BigDecimal monto, String cajero);
}
