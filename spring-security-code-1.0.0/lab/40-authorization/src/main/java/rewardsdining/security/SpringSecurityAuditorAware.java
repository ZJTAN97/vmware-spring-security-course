package rewardsdining.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import rewardsdining.account.Account;

import java.util.Optional;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<Account> {

    @Override
    public Optional<Account> getCurrentAuditor() {
        return SecurityUtils.getCurrentUser();
    }
}
