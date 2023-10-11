package com.drem.fileprocessing.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    final TaskletConfig taskletConfig;
    final StepConfig stepConfig;

    public BatchConfig(@Qualifier("taskletConfig") TaskletConfig taskletConfig,
                       @Qualifier("stepConfig") StepConfig stepConfig) {
        this.taskletConfig = taskletConfig;
        this.stepConfig = stepConfig;
    }

    @Bean
    public JobBuilder job(JobRepository jobRepository) {
        return new JobBuilder("ETL-Load", jobRepository);
    }

    @Bean
    public Job dremJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return job(jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(taskletConfig.stepTasklet(jobRepository, transactionManager))
                .on("COMPLETED").to(stepConfig.stepChunk(jobRepository, transactionManager))
                .on("FAIL").end()
                .end()
                .build();
    }

}
