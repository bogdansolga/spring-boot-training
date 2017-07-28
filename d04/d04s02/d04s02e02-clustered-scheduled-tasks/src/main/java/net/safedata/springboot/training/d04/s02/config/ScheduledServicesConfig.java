package net.safedata.springboot.training.d04.s02.config;

import net.safedata.springboot.training.d04.s02.job.SimpleJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class ScheduledServicesConfig {

    private final SimpleJob simpleJob;

    private Set<JobDetail> jobDetails;
    private Set<Trigger> triggers;

    @Autowired
    public ScheduledServicesConfig(final SimpleJob simpleJob) {
        this.simpleJob = simpleJob;
    }

    @PostConstruct
    public void buildQuartzServicesDetails() {
        jobDetails = new HashSet<>();
        triggers = new HashSet<>();

        addQuartzJobDetail(simpleJob);
    }

    JobDetail[] getJobDetails() {
        return jobDetails.toArray(new JobDetail[jobDetails.size()]);
    }

    Trigger[] getTriggers() {
        return triggers.toArray(new Trigger[triggers.size()]);
    }

    private void addQuartzJobDetail(final SimpleJob simpleJob) {
        jobDetails.add(buildJobDetail(simpleJob));
        triggers.add(buildTrigger(simpleJob));
    }

    @SuppressWarnings("unchecked")
    private JobDetail buildJobDetail(final SimpleJob simpleJob) {
        return JobBuilder.newJob(simpleJob.getClass())
                         .withIdentity(JobKey.jobKey(simpleJob.getName(), simpleJob.getGroup()))
                         .withDescription(simpleJob.getDescription())
                         .storeDurably()
                         .build();
    }

    private Trigger buildTrigger(final SimpleJob simpleJob) {
        return TriggerBuilder.newTrigger()
                             .withIdentity(TriggerKey.triggerKey(simpleJob.getName(), simpleJob.getGroup()))
                             .withDescription(simpleJob.getDescription())
                             .withSchedule(CronScheduleBuilder.cronSchedule(simpleJob.getCronExpression()))
                             .withPriority(Trigger.DEFAULT_PRIORITY)
                             .forJob(JobKey.jobKey(simpleJob.getName(), simpleJob.getGroup()))
                             .build();
    }
}
