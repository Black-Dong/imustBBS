package top.codingdong.imustbbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

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
