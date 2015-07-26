package biz.dealnote.rest.config;

import biz.dealnote.rest.security.ClientAuthenticationFilter;
import biz.dealnote.rest.security.DealNoteUserDetailService;
import biz.dealnote.rest.security.RestAuthenticationService;
import biz.dealnote.web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by lutay.d on 24.07.2015.
 */
@Configuration
@ComponentScan({"biz.dealnote.rest.security"})
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    DealNoteUserDetailService userDetailService;

    @Autowired
    private UserDao userDao;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        RestAuthenticationService authenticationService =  new RestAuthenticationService();
        authenticationService.setAuthenticationManager(authenticationManagerBean());
        authenticationService.setUserDao(userDao);
        http
                .csrf().disable()
                .requiresChannel()
                .anyRequest()
                .requiresSecure()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .addFilterBefore(new ClientAuthenticationFilter(
                        authenticationService
                ), UsernamePasswordAuthenticationFilter.class);
    }
}
