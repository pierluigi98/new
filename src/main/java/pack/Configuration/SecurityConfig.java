package pack.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import pack.Component.SscStatusAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        SscStatusAuthenticationProvider sscStatusAuthenticationProvider= new SscStatusAuthenticationProvider("username","password");
        auth.authenticationProvider(sscStatusAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //permette di generare il token
                .authorizeRequests()
                //.antMatchers("/read").permitAll() //white list
                .anyRequest().authenticated()
                .and().httpBasic();
    }
/* le credenziali inserite nel configure con l'authentication builder e provider hanno la precedenza
su quelli riportati sotto.
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails pierluigi = User.builder().username("pierluigi")
                                                .password(passwordEncoder().encode("password"))
                                                .roles("STUDENT")
                                                .build();
        return new InMemoryUserDetailsManager(pierluigi);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
}


