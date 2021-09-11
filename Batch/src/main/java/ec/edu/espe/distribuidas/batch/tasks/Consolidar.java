package ec.edu.espe.distribuidas.batch.tasks;

import ec.edu.espe.distribuidas.batch.model.Consolidado;
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

@Slf4j
public class Consolidar implements Tasklet, StepExecutionListener {
    private final MongoTemplate mongoTemplate;
    private List<Consolidado> consolidado;

    public Consolidar(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        List<Consolidado> consolidados = mongoTemplate.findAll(Consolidado.class,"Consolidado");
        log.info(consolidados.toString());
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        return null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
