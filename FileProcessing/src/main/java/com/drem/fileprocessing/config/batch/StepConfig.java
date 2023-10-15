package com.drem.fileprocessing.config.batch;

import com.azure.spring.cloud.core.resource.AzureStorageBlobProtocolResolver;
import com.drem.fileprocessing.chunk.listener.Listener;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import static com.drem.fileprocessing.util.constants.FileConstants.*;

@Configuration
public class StepConfig {

    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;
    @Autowired
    private AzureStorageBlobProtocolResolver azureStorageBlobProtocolResolver;

    @Bean
    public FlatFileItemReader<GastosRecipient> reader() {
        return new FlatFileItemReaderBuilder<GastosRecipient>()
                .name("Reader")
                .resource(azureStorageBlobProtocolResolver
                        .getResource(String.format(PATTERN_AZURE_BLOB, containerName,
                                CRETE_FILE_NAME, EXTENSION_FILE_CSV)))
                .linesToSkip(1)
                .delimited()
                .delimiter(DELIMITER_HEADER_CSV)
                .names(HEADER_CSV_CREATE.split(DELIMITER_HEADER_CSV))
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
    public Listener listener() {
        return new Listener();
    }

    @Bean
    public Step stepChunk(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("chunk", jobRepository)
                .<GastosRecipient, Gastos>chunk(5, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .listener(listener())
                .build();
    }


}
