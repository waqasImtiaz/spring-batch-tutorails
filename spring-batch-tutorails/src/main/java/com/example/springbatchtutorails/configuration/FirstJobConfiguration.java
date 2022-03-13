package com.example.springbatchtutorails.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirstJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;


    public FirstJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job helloWorldJob() {
        return jobBuilderFactory.get("helloWorldJob").start(helloWorldStep()).build();
    }

    @Bean
    public Step helloWorldStep() {
        return stepBuilderFactory.get("helloWorldStep").tasklet((stepContribution, chunkContext) -> {
            Object jobParameter = chunkContext.getStepContext().getJobParameters().get("jobParameter");
            System.out.println("Hello World Step with job parameter: " + jobParameter);
            return RepeatStatus.FINISHED;
        }).build();
    }
}
