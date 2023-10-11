package com.drem.fileprocessing.tasklet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JoinFileTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        log.info("entré aquí en el tasklet");
        ClassPathResource resourceIn = new ClassPathResource("csv/input");
        ClassPathResource resourceOut = new ClassPathResource("csv/input/GastosFileLoadData.csv");

        Path inputDirectory = Path.of(resourceIn.getURI());
        File outputFile = resourceOut.getFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            StringBuilder sb = new StringBuilder();
            List<File> csvFiles = Files.walk(inputDirectory, FileVisitOption.FOLLOW_LINKS)
                    .filter(path -> path.toString().endsWith(".csv") && !path.toString().endsWith("GastosFileLoadData.csv"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());

            csvFiles.stream()
                    .map(csvFile -> {
                        try {
                            return Files.readAllLines(Path.of(csvFile.getPath()));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .flatMap(a -> a.stream().map(s -> s.intern()))
                    .forEach(a -> sb.append(a).append('\n'));

            writer.write(sb.toString().trim());
            writer.flush();
        }

        return RepeatStatus.FINISHED;
    }
}
