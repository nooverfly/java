package cn.tarena.fh.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by overfly on 2017/6/6.
 */
@Configuration
public class ShiroConfiguration {
    /**
     * LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
     * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
     * 主要是AuthorizingRealm类的子类，以及EhCacheManager类。
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    //配置自定义的密码比较器
    @Bean(name="credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }

    /**
     * ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，
     * 负责用户的认证和权限的处理，可以参考JdbcRealm的实现。
     */
    @Bean(name = "authRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
        AuthRealm authRealm = new AuthRealm();
//        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

//    /**
//     * EhCacheManager，缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来，
//     * 然后每次用户请求时，放入用户的session中，如果不设置这个bean，每个请求都会查询一次数据库。
//     */
//    @Bean(name = "ehCacheManager")
//    @DependsOn("lifecycleBeanPostProcessor")
//    public EhCacheManager ehCacheManager() {
//        return new EhCacheManager();
//    }

    /**
     * SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类。
     * //
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
//        securityManager.setCacheManager(ehCacheManager());
        return securityManager;
    }

    /**
     * ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。
     * 它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") org.apache.shiro.mgt.SecurityManager manager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(manager);

        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        //配置登录的url和登录成功的url
        //shiroFilterFactoryBean.setLoginUrl("/mlogin.*");

        shiroFilterFactoryBean.setLoginUrl("/mlogin.action");
        shiroFilterFactoryBean.setLoginUrl("/login.html");

        //配置访问权限
        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<String, String>();

        filterChainDefinitionManager.put("/_login.*", "anon"); //表示可以匿名访问
        filterChainDefinitionManager.put("/mlogin.*", "anon"); //表示可以匿名访问
        filterChainDefinitionManager.put("/index.*", "anon"); //表示可以匿名访问
        filterChainDefinitionManager.put("/staticfile/**", "anon");
        filterChainDefinitionManager.put("/*", "authc");//表示需要认证才可以访问
        filterChainDefinitionManager.put("/**", "authc");//表示需要认证才可以访问
        filterChainDefinitionManager.put("/*.*", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);
        return shiroFilterFactoryBean;
    }

    /**
     * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    /**
     * AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，
     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") org.apache.shiro.mgt.SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor aASA = new AuthorizationAttributeSourceAdvisor();
        aASA.setSecurityManager(manager);
        return aASA;
    }

}
