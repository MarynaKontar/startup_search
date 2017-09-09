package ua.goit.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Spring security configuration with Basic login form.
 */
@Configuration
@EnableWebSecurity
@ComponentScan({"ua.goit.services"})
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //TODO 2. дописать правила доступа (antMatchers(...).hasAnyRole(...))
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/show").hasAnyRole("DEVELOPER", "INVESTOR", "ADMIN")
                .antMatchers("/user/**").hasRole("ADMIN")
                .antMatchers("/index.jsp").hasRole("ADMIN")
                .anyRequest().denyAll()
                .and().formLogin()
                .and().csrf().disable();

//        http.authorizeRequests()
//                .antMatchers("/registration", "/register").not().authenticated()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .and()
//                .csrf().disable()

    }
}
