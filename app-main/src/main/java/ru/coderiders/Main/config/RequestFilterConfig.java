package ru.coderiders.Main.config;

import ru.coderiders.Main.rest.filter.RequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestFilterConfig {

    @Bean
    public FilterRegistrationBean<RequestFilter> hivePathLoggingFilter() {
        FilterRegistrationBean<RequestFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestFilter());
        registrationBean.addUrlPatterns("/api/bee_types/*");
        registrationBean.addUrlPatterns("/api/hives/*");
        registrationBean.addUrlPatterns("/api/bee_families/*");

        return registrationBean;
    }

}
