package com.example.BiteMeBee.config;

import com.example.BiteMeBee.rest.filter.HiveRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HivePathFilterConfig {

    @Bean
    public FilterRegistrationBean<HiveRequestFilter> hivePathLoggingFilter(){
        FilterRegistrationBean<HiveRequestFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new HiveRequestFilter());
        registrationBean.addUrlPatterns("/api/hives/*");

        return registrationBean;
    }
}
