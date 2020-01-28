package top.codingdong.imustbbs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.codingdong.imustbbs.interceptor.LoginInterceptor;

/**
 * @author Dong
 * @date 2020/1/28 14:53
 */
//@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/own/**","/post/**");
    }

}
