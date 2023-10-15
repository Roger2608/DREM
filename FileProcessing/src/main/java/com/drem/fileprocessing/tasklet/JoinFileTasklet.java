package com.drem.fileprocessing.tasklet;

import com.azure.spring.cloud.core.resource.AzureStorageBlobProtocolResolver;
import com.drem.fileprocessing.util.CrudFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.nio.charset.Charset;

import static com.drem.fileprocessing.util.constants.FileConstants.*;

@Slf4j
public class JoinFileTasklet implements Tasklet, StepExecutionListener {

    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private AzureStorageBlobProtocolResolver azureStorageBlobProtocolResolver;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        log.info("entré aquí en el tasklet");

        Resource[] resources = azureStorageBlobProtocolResolver.getResources(
                String.format(PATTERN_AZURE_BLOB, containerName, "*"));
        log.info("{} resources founded with pattern:*.csv, \n all are: \n {}", resources.length, resources);

        StringBuilder sb = new StringBuilder();
        sb.append(HEADER_CSV_CREATE);

        for (Resource resource : resources) {
            InputStream a = resource.getInputStream();
            log.info("el nombre del archivo leyendo es: {} ", resource.getFilename());
            sb.append("\n");
            sb.append(StreamUtils.copyToString(a, Charset.defaultCharset()));
        }

        CrudFile.createNewFile(resourceLoader, containerName, CRETE_FILE_NAME, sb.toString().trim());

        return RepeatStatus.FINISHED;
    }

}
