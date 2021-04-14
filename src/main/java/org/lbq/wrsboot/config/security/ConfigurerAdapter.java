package org.lbq.wrsboot.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lbq.wrsboot.Vo.RespVo;
import org.lbq.wrsboot.bean.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.Arrays;

@Configuration
public class ConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userServiceConfig;


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/f/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceConfig);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")  //请求的登陆页路径
                .loginProcessingUrl("/dologin")  //请求的登陆页接口
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        Hr hr = (Hr) authentication.getPrincipal();
                        RespVo respVo = new RespVo(200, "登陆成功", hr);
                        String s = new ObjectMapper().writeValueAsString(respVo);
                        PrintWriter writer = response.getWriter();
                        writer.write(s);
                        writer.flush();
                        writer.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = response.getWriter();
                        RespVo respVo = null;
                        if (exception instanceof LockedException) {
                            respVo = new RespVo(500, "账号被锁定，请联系管理员", "");
                        } else if (exception instanceof DisabledException) {
                            respVo = new RespVo(500, "账号被禁用，请联系管理员", "");
                        } else if (exception instanceof CredentialsContainer) {
                            respVo = new RespVo(500, "密码过期，请联系管理员", "");
                        } else if (exception instanceof AccountExpiredException) {
                            respVo = new RespVo(500, "账号过期，请联系管理员", "");
                        } else if (exception instanceof BadCredentialsException) {
                            respVo = new RespVo(500, "用户名、密码错误，请重新输入", "");
                        }
                        String s = new ObjectMapper().writeValueAsString(respVo);
                        writer.write(s);
                        writer.flush();
                        writer.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        RespVo respVo = new RespVo(200, "注销成功", "");
                        String s = new ObjectMapper().writeValueAsString(respVo);
                        PrintWriter writer = response.getWriter();
                        writer.write(s);
                        writer.flush();
                        writer.close();
                    }
                })
                .clearAuthentication(true)  //注销时，清除用户信息
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        System.out.println(authException.getMessage() + "------------------------------");
                        response.setStatus(401);
                        RespVo respVo = null;
                        if (authException instanceof InsufficientAuthenticationException) {
                            respVo = new RespVo(401, "您未登陆，请求资源失败", "");
                        }
                        String s = new ObjectMapper().writeValueAsString(respVo);
                        PrintWriter writer = response.getWriter();
                        writer.write(s);
                        writer.flush();
                        writer.close();
                    }
                });
    }

}
