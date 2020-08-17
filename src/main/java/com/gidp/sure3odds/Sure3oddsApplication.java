package com.gidp.sure3odds;

import com.gidp.sure3odds.config.RequestFilterConfig;
import com.gidp.sure3odds.filter.RequestFilter;
import com.gidp.sure3odds.service.users.AuthenticationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Sure3oddsApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sure3oddsApplication.class, args);
    }
    @Bean
    public FilterRegistrationBean<RequestFilter> requestSignatureFilterFilterRegistrationBean(RequestFilterConfig requestSignatureFilterConfig, AuthenticationService authenticationService) {
        RequestFilter requestSignatureFilter = new RequestFilter(requestSignatureFilterConfig,authenticationService);
        FilterRegistrationBean<RequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(requestSignatureFilter);
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registrationBean.setAsyncSupported(false);
        return registrationBean;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
