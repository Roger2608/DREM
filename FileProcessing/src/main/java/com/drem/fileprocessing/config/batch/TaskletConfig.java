package com.drem.fileprocessing.config.batch;

import com.drem.fileprocessing.tasklet.JoinFileTasklet;
import lombok.NoArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TaskletConfig {

    @Bean
    public Tasklet tasklet() {
        return new JoinFileTasklet();
    }

    @Bean
    public Step stepTasklet(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("task",jobRepository)
                .tasklet(tasklet(),transactionManager)
                .build();
    }

}
