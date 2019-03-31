package bo.com.cognos.java.proyecto.jobs;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

public class AutowiringSpringBeanJobFactory 
extends SpringBeanJobFactory 
implements ApplicationContextAware{

	private transient AutowireCapableBeanFactory beanFactory;
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		beanFactory = context.getAutowireCapableBeanFactory();
	}
	  
	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		final Object job = super.createJobInstance(bundle);
	    beanFactory.autowireBean(job);
	    return job;
	}

	
}
