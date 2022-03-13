package com.example.springbatchtutorails.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecondJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public SecondJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job secondJob() {
        return jobBuilderFactory.get("secondJob").start(getFirstStep()).build();
    }

    private Step getFirstStep() {
        return stepBuilderFactory.get("firstStep").tasklet((stepContribution, chunkContext) -> {
            Object secondJobParameter = chunkContext.getStepContext().getJobParameters().get("secondJobParameter");
            System.out.println("SecondJob firstStep: " + secondJobParameter);
            return RepeatStatus.FINISHED;
        }).build();
    }
}
