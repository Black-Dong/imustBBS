package top.codingdong.imustbbs.shiro.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import top.codingdong.imustbbs.shiro.realm.MyRealm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置类
 *
 * Filter Chain 定义说明
 * 一个URL可以配置多个Filter，使用逗号分割
 * 当设置多个过滤器时，全部通过才视为通过
 * 部分过滤器可以指定参数，如perms,roles
 *
 * @author Dong
 * @date 2020/2/26 16:19
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 如果不设置，默认自动寻找Web工程根目录下的"login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/");

        // 拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 过滤器定义，从上往下执行，一般将/**放在最下边，    天坑
        // authc: 所有的url必须认证通过才可以访问， anon: 所有url都可以匿名访问
        // 配置不会被拦截的连接，顺序判断
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/layui/**", "anon");
        filterChainDefinitionMap.put("/fly-layui/**", "anon");
        filterChainDefinitionMap.put("/user/register", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/register", "anon");
        filterChainDefinitionMap.put("/search/**", "anon");
        filterChainDefinitionMap.put("/findPassword", "anon");
        filterChainDefinitionMap.put("/user/sendEmail", "anon");
        filterChainDefinitionMap.put("/user/checkYzm", "anon");
        filterChainDefinitionMap.put("/user/detail/**", "anon");
        filterChainDefinitionMap.put("/detail/**", "anon");
        filterChainDefinitionMap.put("/category/**", "anon");

        // 配置退出过滤器, 其中具体的退出代码shiro已实现
        filterChainDefinitionMap.put("/user/logout", "logout");

        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 设置realm
        securityManager.setRealm(myRealm());
        return securityManager;
    }

    /**
     * 身份验证realm
     *
     * @return
     */
    @Bean
    public MyRealm myRealm() {
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }


    /**
     * Shiro生命周期
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {

        return new LifecycleBeanPostProcessor();
    }


    /**
     * 开启Shiro注解 (@RequiresRoles @RequiresPermissions),
     *      需要借助SpringAOP扫描使用Shiro注解的类，并在必要时进行安全逻辑认证
     * DefaultAdvisorAutoProxyCreator
     * AuthorizationAttributeSourceAdvisor
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

}
