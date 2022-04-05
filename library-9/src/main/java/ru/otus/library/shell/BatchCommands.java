package ru.otus.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@RequiredArgsConstructor
@ShellComponent
public class BatchCommands {
    private final Job importUserJob;
    private final JobLauncher jobLauncher;

    @ShellMethod(value = "Start DB migration", key = "m -s")
    public String startMigrationJob() throws Exception {
        JobExecution execution = jobLauncher.run(importUserJob, new JobParametersBuilder().toJobParameters());
        return String.format("The end DB migration with code status ----> %s", execution.getExitStatus());
    }
}
