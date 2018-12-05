package net.safedata.springboot.training.d04.s02.config;

import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

@Configuration
@AutoConfigureAfter(value = {ScheduledServicesConfig.class, TaskExecutorConfig.class})
public class QuartzConfig {

    private final ScheduledServicesConfig scheduledServicesConfig;
    private final TaskExecutor taskExecutor;

    @Autowired
    public QuartzConfig(final ScheduledServicesConfig scheduledServicesConfig,
                        final TaskExecutor taskExecutor) {
        this.scheduledServicesConfig = scheduledServicesConfig;
        this.taskExecutor = taskExecutor;
    }

    @Bean
    public JobFactory jobFactory(final ApplicationContext applicationContext) {
        final AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(final JobFactory jobFactory) throws IOException {
        final SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();

        // allows to update triggers in DB when updating settings in config file:
        schedulerFactoryBean.setSchedulerName("quartz-scheduler-01");
        schedulerFactoryBean.setQuartzProperties(quartzProperties());

        // allows to update triggers in DB when updating settings in config file:
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setJobFactory(jobFactory);

        //schedulerFactoryBean.setDataSource(dataSource());
        //schedulerFactoryBean.setTransactionManager(transactionManager());

        schedulerFactoryBean.setTriggers(scheduledServicesConfig.getTriggers());
        schedulerFactoryBean.setJobDetails(scheduledServicesConfig.getJobDetails());
        schedulerFactoryBean.setTaskExecutor(taskExecutor);

        schedulerFactoryBean.setAutoStartup(true);

        return schedulerFactoryBean;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        final PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
}
