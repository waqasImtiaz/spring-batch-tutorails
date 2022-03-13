package com.example.springbatchtutorails;

import com.example.springbatchtutorails.runner.TaskRunner;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@EnableAutoConfiguration
@ComponentScan(basePackageClasses = MainApplication.class)
@EntityScan(basePackageClasses = MainApplication.class)
@EnableBatchProcessing
@PropertySource("application.properties")
public class MainApplication {

    public static void main(String[] args) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException,
            JobParametersInvalidException, JobRestartException, InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);
        TaskRunner taskRunner = context.getBean(TaskRunner.class);
        taskRunner.run();
    }
}
