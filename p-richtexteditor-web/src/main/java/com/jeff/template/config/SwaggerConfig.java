package com.jeff.template.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Properties;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Autowired
	@Qualifier("propertiesConfig")
	private Properties propertiesConfig; 

	@Bean
	public Docket newsApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		String flag = propertiesConfig.getProperty("swaggerUi.local.enable");
		if("true".equals(flag)) {
			docket.enable(true);
		}
		else {
			docket.enable(false);
		}
		
		docket.apiInfo(apiInfo()).select().paths(PathSelectors.any()).build();
		return docket;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("接口测试平台").description("在这里你可以浏览项目所有接口，并提供相关测试工具")
				.termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open").contact("张友朋")
				.license("Version 1.0").licenseUrl("#").version("1.0").build();
	}
}
