package com.example.springbatchtutorails;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootTest(classes = LectureFourTest.TestConfig.class)
public class LectureFourTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    void helloWorldTest() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().addParameter("helloWorldParam", new JobParameter("jobParam")).
                toJobParameters();

        jobLauncherTestUtils.launchJob(jobParameters);
    }

    @Configuration
    @EnableBatchProcessing
    static class TestConfig {

        @Autowired
        private JobBuilderFactory jobBuilderFactory;

        @Autowired
        private StepBuilderFactory stepBuilderFactory;

        private Step helloWorldStep() {
            return stepBuilderFactory.get("helloWorldStep").tasklet((stepContribution, chunkContext) -> {
                Object helloWorldParam = stepContribution.getStepExecution().getJobExecution().getExecutionContext().get("helloWorldParam");
                System.out.println("Hello World batch with job param: " + helloWorldParam);
                return RepeatStatus.FINISHED;
            }).build();
        }

        @Bean
        public Job helloWorldJob() {
            return jobBuilderFactory.get("helloWorldJob").start(helloWorldStep()).build();
        }

        @Bean
        public JobLauncherTestUtils getJobLauncherTestUtils() {
            return new JobLauncherTestUtils();
        }
    }
}
