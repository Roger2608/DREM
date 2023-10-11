package com.drem.fileprocessing.controller;

//import org.springframework.batch.core.BatchStatus;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobExecution;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.explore.JobExplorer;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Mono;
//
//import java.util.Map;

//@RestController
//@RequestMapping("batch")
public class JobController {

//    @Autowired
//    JobLauncher jobLoteMovimientosLauncher;
//
//    @Autowired
//    Job dremJob;
//
//    @Autowired
//    private JobExplorer jobExplorer;
//
//    @PostMapping("start")
//    public Mono<Long> demoJob(@RequestBody Map<String,String> parameters) throws Exception {
//
//        JobExecution jobExecution = (JobExecution) jobLoteMovimientosLauncher.run(dremJob,
//                new JobParametersBuilder()
//                        .addLong("idInicio", System.nanoTime())
//                        .addString("inputfile",parameters.get("inputfile"))
//                        .toJobParameters());
//        return Mono.just(jobExecution.getId());
//    }
//
//
//    @GetMapping("/status")
//    public Mono<String> getJobStatus(@RequestParam("jobId") Long jobId) {
//        JobExecution jobExecution = jobExplorer.getJobExecution(jobId);
//
//        if (jobExecution == null) {
//            return Mono.just("Job no encontrado");
//        }
//
//        BatchStatus batchStatus = jobExecution.getStatus();
//
//        return Mono.just("Estado del Job: " + batchStatus.toString());
//    }

}
