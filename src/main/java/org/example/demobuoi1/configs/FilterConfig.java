package org.example.demobuoi1.configs;

import org.example.demobuoi1.security.AuthorFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<AuthorFilter> authorFilterBean(){
        FilterRegistrationBean<AuthorFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new AuthorFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }
}
