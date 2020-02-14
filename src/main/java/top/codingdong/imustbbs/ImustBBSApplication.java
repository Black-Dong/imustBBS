package top.codingdong.imustbbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Dong
 * @date 2020/1/8 17:54
 */
@SpringBootApplication
@MapperScan("top.codingdong.imustbbs.mapper")
public class ImustBBSApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImustBBSApplication.class, args);
    }
}
