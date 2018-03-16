package org.apereo.cas.config;

import lombok.extern.slf4j.Slf4j;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.reports.RegisteredServicesReportEndpoint;
import org.apereo.cas.reports.SpringWebflowReportEndpoint;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This this {@link CasReportsConfiguration}.
 *
 * @author Misagh Moayyed
 * @author Dmitriy Kopylenko
 * @since 5.3.0
 */
@Configuration("casReportsConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
@Slf4j
public class CasReportsConfiguration {

    @Autowired
    private CasConfigurationProperties casProperties;

    @Autowired
    @Qualifier("servicesManager")
    private ServicesManager servicesManager;
    
    @Bean
    @ConditionalOnEnabledEndpoint
    public SpringWebflowReportEndpoint springWebflowEndpoint() {
        return new SpringWebflowReportEndpoint();
    }

    @Bean
    @ConditionalOnEnabledEndpoint
    public RegisteredServicesReportEndpoint registeredServicesReportEndpoint() {
        return new RegisteredServicesReportEndpoint(servicesManager);
    }

}
