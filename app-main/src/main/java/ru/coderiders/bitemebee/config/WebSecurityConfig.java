package ru.coderiders.bitemebee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.coderiders.bitemebee.entity.Role;
import ru.coderiders.bitemebee.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                //Доступ только для не зарегистрированных пользователей
                .antMatchers("/api/registration", "/api/login").not().fullyAuthenticated()
                //Доступ только для пользователей с ролью Администратор
                .antMatchers("/api/hives").hasRole("USER")
                //Доступ разрешен всем пользователей
                .antMatchers("/", "/resources/**", "/swagger-ui/*", "/v3/**").permitAll()
                //Все остальные страницы требуют аутентификации
                .anyRequest().authenticated();

        /*
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/registration").not().fullyAuthenticated()
                    .antMatchers("/swagger-ui/*", "/v3/**", "/api/login", "/api/logout").permitAll()
                .anyRequest().authenticated();
                //.antMatchers("/api/*").fullyAuthenticated()
               // .antMatchers("/swagger-ui/*").permitAll();


        httpSecurity
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/api/hives", "/swagger-ui/*").permitAll()
                .anyRequest().authenticated(); *\


/*
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                //Доступ только для не зарегистрированных пользователей
                //  .antMatchers("/api/registration").not().fullyAuthenticated()
                //Доступ только для пользователей с ролью Администратор
             //   .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/api/hives").hasRole("USER")
                //Доступ разрешен всем пользователей
                .antMatchers("/", "/resources/**").permitAll(); */
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
        auth.inMemoryAuthentication()
                .withUser("admin").password(bCryptPasswordEncoder().encode("admin")).roles("ADMIN");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
