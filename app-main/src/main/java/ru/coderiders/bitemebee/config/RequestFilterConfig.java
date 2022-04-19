package ru.coderiders.bitemebee.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.coderiders.bitemebee.rest.filter.RequestFilter;

@Configuration
public class RequestFilterConfig {
    @Bean
    public FilterRegistrationBean<RequestFilter> hivePathLoggingFilter() {
        FilterRegistrationBean<RequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestFilter());
        registrationBean.addUrlPatterns("/api/bee_types");
        registrationBean.addUrlPatterns("/api/bee_types/*");
        registrationBean.addUrlPatterns("/api/hives");
        registrationBean.addUrlPatterns("/api/hives/*");
        registrationBean.addUrlPatterns("/api/bee_families");
        registrationBean.addUrlPatterns("/api/bee_families/*");
        registrationBean.addUrlPatterns("/api/activities");
        registrationBean.addUrlPatterns("/api/activities/*");
        registrationBean.addUrlPatterns("/api/schedules");
        registrationBean.addUrlPatterns("/api/schedules/*");
        registrationBean.addUrlPatterns("/api/generator_panel");
        registrationBean.addUrlPatterns("/api/jobs");
        registrationBean.addUrlPatterns("/api/jobs/*");
        registrationBean.addUrlPatterns("/api/users");
        registrationBean.addUrlPatterns("/api/users/*");
        return registrationBean;
    }
}
