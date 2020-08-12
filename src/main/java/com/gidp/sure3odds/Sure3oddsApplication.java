package com.gidp.sure3odds;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication

@EnableSwagger2
public class Sure3oddsApplication {
	 OkHttp3ClientHttpRequestFactory client = new OkHttp3ClientHttpRequestFactory();
	 
	 
	public static void main(String[] args) {
		SpringApplication.run(Sure3oddsApplication.class, args);
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
	  ApplicationRunner init() {

		return null;
	  }

}
