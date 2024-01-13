package rewardsdining.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import rewardsdining.security.CustomAuthenticationSuccessHandler;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(requests -> requests
                        .mvcMatchers("/login", "/logout").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(withDefaults())
                .formLogin(login -> login.loginPage("/login")
                        .usernameParameter("user")
                        .passwordParameter("pass")
                        .defaultSuccessUrl("/restaurants")
                        .successHandler(new CustomAuthenticationSuccessHandler())
                )
                .logout(withDefaults());

        return http.build();
    }

    @Bean
    public InMemoryAuditEventRepository repository() {
        return new InMemoryAuditEventRepository();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}

