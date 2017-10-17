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
                .antMatchers("/","/*.css", "/*.jpg","/error").permitAll()
                .antMatchers("/registration/", "/registrationAfterMissingLogin/").not().authenticated()
                .antMatchers("/user/users/").hasRole("ADMIN")
                .antMatchers("/startup/*", "/startup/**", "/interest/*", "/interest/**",
                        "/user/*", "/user/**", "/storage/*","/storage/**").authenticated()

                .anyRequest().denyAll()
                .and().formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/")
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
