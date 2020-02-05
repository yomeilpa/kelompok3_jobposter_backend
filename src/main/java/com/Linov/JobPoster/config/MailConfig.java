package com.Linov.JobPoster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@Configuration
public class MailConfig {
		
		 @Bean(name="emailConfigBean")
		    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration(ResourceLoader resourceLoader) {
		        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
		        bean.setTemplateLoaderPath("classpath:/html/");
		        return bean;
		    }

}
