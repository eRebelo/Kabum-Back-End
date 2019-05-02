package com.eduardo.kabum.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket kabumApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.eduardo.kabum")).paths(regex("/kabum/api.*")).build().apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo("Kabum API REST", "API REST of register of customers.", "1.0", "Terms of Service", new Contact("Eduardo Rebelo", "https://www.linkedin.com/in/eduardo-rebelo", ""),
				"Apache License Version 2.0", "https://apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());

		return apiInfo;
	}
}