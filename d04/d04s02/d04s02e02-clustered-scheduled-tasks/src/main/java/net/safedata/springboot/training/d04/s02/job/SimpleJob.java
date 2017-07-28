package net.safedata.springboot.training.d04.s02.job;

import net.safedata.springboot.training.d04.s02.task.SimpleTask;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SimpleJob implements Job {

    @Autowired
    private SimpleTask simpleTask;

    @Value("${simple.job.cron.expression}")
    private String cronExpression;

    @Override
    public void execute(final JobExecutionContext jobExecutionContext) throws JobExecutionException {
        simpleTask.performScheduledTask();
    }

    public String getName() {
        return "Simple Quartz job";
    }

    public String getGroup() {
        return "1st group";
    }

    public String getDescription() {
        return "A very simple job";
    }

    public String getCronExpression() {
        return cronExpression;
    }
}
