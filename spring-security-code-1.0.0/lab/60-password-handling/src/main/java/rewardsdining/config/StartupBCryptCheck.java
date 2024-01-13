package rewardsdining.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rewardsdining.security.BCryptStrengthTester;

@Component
public class StartupBCryptCheck implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        BCryptStrengthTester.startTest();

    }
}
