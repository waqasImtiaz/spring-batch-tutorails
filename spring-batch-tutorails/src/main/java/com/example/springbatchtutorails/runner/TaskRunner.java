package com.example.springbatchtutorails.runner;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Component;

@Component
public class TaskRunner {

    private final JobLauncher jobLauncher;
    private final Job job;

    public TaskRunner(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    public void run() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException,
            JobParametersInvalidException, JobRestartException, InterruptedException {
        JobParameters jobParameters = new JobParametersBuilder().addParameter("jobParameter",
                new JobParameter("helloWorldJobParameter")).toJobParameters();
        jobLauncher.run(job, jobParameters);

        Thread.sleep(3000);

        JobParameters secondRunJobParameters = new JobParametersBuilder().addParameter("jobParameter",
                new JobParameter("second run")).toJobParameters();
        jobLauncher.run(job, secondRunJobParameters);
    }
}
