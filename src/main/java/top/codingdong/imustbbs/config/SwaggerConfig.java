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
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Dong
 * @date 2020/2/17 9:41
 */
@Configuration
@EnableSwagger2
@PropertySource(value = "classpath:/config/swagger.property", encoding = "utf-8")
public class SwaggerConfig {

    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.author.name}")
    private String authorName;

    @Value("${swagger.description}")
    private String description;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .tags(new Tag("login", "登录注册相关接口"), getTags())
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.codingdong.imustbbs.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    private Tag[] getTags() {
        Tag[] tags = {
                new Tag("category", "分类接口"),
                new Tag("admin_category", "分类管理接口"),
                new Tag("api_product", "产品内部接口")
        };
        return tags;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .contact(new Contact(authorName, "", ""))
                .description(description)
                .build();
    }
}
