package com.drem.fileprocessing.config.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final TaskletConfig taskletConfig;
    private final StepConfig stepConfig;

    @Bean
    public JobBuilder job(JobRepository jobRepository) {
        return new JobBuilder("ETL-Load", jobRepository);
    }

    @Bean
    public Job dremJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return job(jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(taskletConfig.stepTasklet(jobRepository, transactionManager))
                .on("FAIL").end()
                .on("COMPLETED").to(stepConfig.stepChunk(jobRepository, transactionManager))
                .end()
                .build();
    }

}
