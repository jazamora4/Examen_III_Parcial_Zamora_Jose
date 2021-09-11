package ec.edu.espe.distribuidas.batch.tasks;

import ec.edu.espe.distribuidas.batch.model.ATM;
import ec.edu.espe.distribuidas.batch.model.Consolidado;
import ec.edu.espe.distribuidas.batch.model.Transaccion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Slf4j
public class Consolidar implements Tasklet, StepExecutionListener {
    private final MongoTemplate mongoTemplate;
    private List<Consolidado> consolidado;
    private List<Consolidado> consolidadoAnterior;
    private List<Transaccion> transaccions;


    public Consolidar(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        Query query = new Query();
        query.addCriteria(Criteria.where("estado").is("NCO"));
        this.transaccions = mongoTemplate.find(query, Transaccion.class);
        this.consolidadoAnterior = this.consolidadosAnteriores();
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        for(Consolidado consolidado: this.consolidadoAnterior){
            BigDecimal disponible = consolidado.getDisponible();
            Consolidado newConsolidado = new Consolidado();
            newConsolidado.setCajero(consolidado.getCajero());
            newConsolidado.setTimestamp(new Date());
            newConsolidado.setBilletes(consolidado.getBilletes());
            List<Transaccion> updateTransactions = new ArrayList<>();
            for(Transaccion tx: this.transaccions){
                if("RET".equals(tx.getTipo())){
                    disponible.subtract(tx.getMonto());
                    newConsolidado.getBilletes().setDeno10(
                            newConsolidado.getBilletes().getDeno10()-tx.getBilletes().getDeno10());
                    newConsolidado.getBilletes().setDeno20(
                            newConsolidado.getBilletes().getDeno20()-tx.getBilletes().getDeno20());
                }else if("DEP".equals(tx.getTipo())){
                    disponible.add(tx.getMonto());
                    newConsolidado.getBilletes().setDeno10(
                            newConsolidado.getBilletes().getDeno10()+tx.getBilletes().getDeno10());
                    newConsolidado.getBilletes().setDeno20(
                            newConsolidado.getBilletes().getDeno20()+tx.getBilletes().getDeno20());
                }
            }
            newConsolidado.setDisponible(disponible);
            this.mongoTemplate.save(newConsolidado);
        }
        return null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    private List<Consolidado> consolidadosAnteriores(){

        List<ATM> cajeros = this.mongoTemplate.findAll(ATM.class,"ATM");
        List<Consolidado> response = new ArrayList<>();
        for (ATM cajero: cajeros){
            Query query = new Query();
            query.addCriteria(Criteria.where("cajero").is(cajero.getId()));
            List<Consolidado> consolidados = this.mongoTemplate.find(query,Consolidado.class);
            Consolidado consolidado = consolidados.get(consolidados.size()-1);
            response.add(consolidado);
        }
        return response;
    }
}
