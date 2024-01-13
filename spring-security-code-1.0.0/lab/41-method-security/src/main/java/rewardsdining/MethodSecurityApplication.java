package rewardsdining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
public class MethodSecurityApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(MethodSecurityApplication.class, args);
    }
}