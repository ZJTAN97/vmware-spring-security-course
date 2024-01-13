package rewardsdining.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Component;
import rewardsdining.account.Account;
import rewardsdining.account.data.AccountRepository;

@Component
public class UpgradePasswordService implements UserDetailsPasswordService {

    // Spring Security delegates to UserDetailsPasswordService after successful authentication (slide 279)

    private final AccountRepository accountRepository;


    public UpgradePasswordService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {

        Account account = accountRepository.findByUsername(user.getUsername()).orElseThrow();
        account.setPassword(newPassword);
        accountRepository.save(account);

        return new AccountUserDetails(account);
    }
}
