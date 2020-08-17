package com.gidp.sure3odds;

import com.gidp.sure3odds.config.RequestFilterConfig;
import com.gidp.sure3odds.filter.RequestFilter;
import com.gidp.sure3odds.service.users.AuthenticationService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication

@EnableSwagger2
public class Sure3oddsApplication {


    public static void main(String[] args) {

        SpringApplication.run(Sure3oddsApplication.class, args);
        //http://localhost:8080/swagger-ui.html
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Sure3Odds API").license("").licenseUrl("").termsOfServiceUrl("")
                .version("1.0").build();
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


    @Bean
    ApplicationRunner init() {

        return null;
    }

}
