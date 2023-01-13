package com.ssssv.swagger.config;

import com.ssssv.swagger.config.bean.SwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .contact(new Contact(
                        swaggerProperties.getContactName(),
                        swaggerProperties.getContactUrl(),
                        swaggerProperties.getContactEmail()))
                .version(swaggerProperties.getVersion())
                .description(swaggerProperties.getDescription())
                .build();

    }
}
