package top.codingdong.imustbbs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Dong
 * @date 2020/2/17 9:41
 */
@Configuration
@EnableSwagger2
@PropertySource(value = "classpath:/config/swagger.property",encoding = "utf-8")
public class SwaggerConfig {

    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.author}")
    private String author;

    @Value("${swagger.description}")
    private String description;

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.codingdong.imustbbs.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .title(title)
                .contact(new Contact(author,"",""))
                .description(description)
                .build();
    }
}
