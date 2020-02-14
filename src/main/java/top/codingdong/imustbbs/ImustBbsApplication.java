package top.codingdong.imustbbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Dong
 * @date 2020/1/8 17:54
 */
@SpringBootApplication
@MapperScan(basePackages = "top.codingdong.imustbbs.mapper")
public class ImustBbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImustBbsApplication.class, args);
    }
}
