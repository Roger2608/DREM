package com.drem.fileprocessing.config;

import com.drem.fileprocessing.chunk.processor.GastosProcessor;
import com.drem.fileprocessing.chunk.writer.GastosWriter;
import com.drem.fileprocessing.entities.Gastos;
import com.drem.fileprocessing.recipient.GastosRecipient;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class StepConfig {

    @Bean
    public FlatFileItemReader<GastosRecipient> reader() {
        return new FlatFileItemReaderBuilder<GastosRecipient>()
                .name("Reader")
                .resource(new ClassPathResource("csv/input/GastosFileLoadData.csv"))
                .linesToSkip(1)
                .delimited()
                .delimiter("|")
                .names(new String[]{"texto"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<GastosRecipient>() {{
                    setTargetType(GastosRecipient.class);
                }})
                .build();
    }

    @Bean
    public GastosProcessor processor() {
        return new GastosProcessor();
    }

    @Bean
    public GastosWriter writer() {
        return new GastosWriter();
    }

    @Bean
    public Step stepChunk(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("chunk",jobRepository)
                .<GastosRecipient, Gastos>chunk(2, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }


}
