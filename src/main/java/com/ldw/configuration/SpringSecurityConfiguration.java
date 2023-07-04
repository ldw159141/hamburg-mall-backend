package com.ldw.configuration;

import com.ldw.filter.TokenLoginFilter;
import com.ldw.filter.TokenVerifyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


/**
 * SpringSecurity配置类
 * @author HP刘德伟
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }


    /**
     * 不推荐使用，但是由于自定义了过滤器，在此写白名单
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/goods/**","/goodsimg/**")//方法白名单
                .antMatchers("/doc.html", "/doc.html/**", "/webjars/**", "/v2/**", "/v3/**",
                        "/swagger-ui.html", "/swagger-resources", "/swagger-resources/**", "/swagger-ui.html/**")//doc和swagger白名单
         //       .antMatchers("/uploads","/views")
                .antMatchers("/image/**");//静态资源白名单
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .csrf().disable()
               .authorizeRequests()
               .antMatchers("/user/**").hasRole("ADMIN")//只有ADMIN能操作接口下的请求

//               .antMatchers("/image/**").permitAll
               //因为自定义了过滤器，如果要白名单绕开就重写configure(WebSecurity web)

               .anyRequest().authenticated()
               .and()
               .cors().configurationSource(configurationSource())
               .and()
               .addFilter(new TokenLoginFilter(super.authenticationManager()))
               .addFilter(new TokenVerifyFilter(super.authenticationManager()))
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//关闭session
    }

    /**
     * 设置跨域信息，已经再CorsConfiguration中配置跨越但是失效了，再次配置
     * @return
     */    CorsConfigurationSource configurationSource(){
        CorsConfiguration config=new CorsConfiguration();
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);
        return source;
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("ldw"));
    }

}
