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
        http.authorizeRequests() .antMatchers("/", "/startup").permitAll()
                .antMatchers("/registration", "/registration/**").not().authenticated()
                .antMatchers("/main/").authenticated()
                .antMatchers("/startup/**", "/user/**", "/user/*", "/startup/*").authenticated()
                .antMatchers("/startup/**", "/user/**", "/user/*", "/startup/*").permitAll()
                .antMatchers("/main/").permitAll()
                .antMatchers("/*.css").permitAll()
                .antMatchers("/index.jsp").hasRole("ADMIN")

                .anyRequest().denyAll()
                .and().formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/main/")
                .permitAll()
                .and()
                // разрешаем делать логаут всем
                .logout().permitAll()
                // указываем URL логаута
                .logoutUrl("/logout")
                // указываем URL при удачном логауте
//                .logoutSuccessUrl("/login?logout")
                .logoutSuccessUrl("/")
                // делаем не валидной текущую сессию
                .invalidateHttpSession(true).permitAll()
                .and()
                .csrf().disable();
    }
}
