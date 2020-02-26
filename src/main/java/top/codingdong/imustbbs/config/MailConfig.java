package top.codingdong.imustbbs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 邮件配置类
 * @author Dong
 * @date 2020/2/26 18:43
 */
@Configuration
public class MailConfig {

    /**
     * 获取邮件发送实例
     */
    @Bean
    public MailSender mailSender(){

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // 知道用来发送email的邮件服务器主机名
        mailSender.setHost("smtp.126.com");
        //默认端口，标准的SMTP端口
        mailSender.setPort(25);
        mailSender.setUsername("imust_bbs@126.com");
        mailSender.setPassword("imustBBS01");

        return mailSender;
    }

}
