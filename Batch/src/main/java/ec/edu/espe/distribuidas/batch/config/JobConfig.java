package ec.edu.espe.distribuidas.batch.config;

import ec.edu.espe.distribuidas.batch.tasks.Consolidar;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
public class JobConfig {
    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    protected Step consolidar() {
        return steps
                .get("stepPrueba")
                .tasklet(new Consolidar(this.mongoTemplate))
                .build();
    }

    @Bean
    public Job consolidacion() {
        return jobs
                .get("consolidacion")
                .start(consolidar())
                .build();
    }


}
