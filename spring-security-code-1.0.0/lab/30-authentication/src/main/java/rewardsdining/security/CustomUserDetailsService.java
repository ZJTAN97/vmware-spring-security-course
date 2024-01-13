package rewardsdining.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import rewardsdining.account.data.AccountRepository;

public class CustomUserDetailsService implements UserDetailsService {


    // By default, this will be wrapped with DaoAuthenticationProvider (if CustomAuthenticationProvider is not done)

    private final AccountRepository accountRepository;


    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username)
                .map(AccountUserDetails::from)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
    }
}