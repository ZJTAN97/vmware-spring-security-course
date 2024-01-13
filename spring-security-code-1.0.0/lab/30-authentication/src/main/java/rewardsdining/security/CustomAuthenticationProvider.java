package rewardsdining.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import rewardsdining.account.data.AccountRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final CustomUserDetailsService userDetailsService;
	
	private final PasswordEncoder passwordEncoder;
	
	
	public CustomAuthenticationProvider(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
		this.userDetailsService = new CustomUserDetailsService(accountRepository);
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName(); // Obtain the user name from the Authentication object
		
		String password = authentication.getCredentials().toString(); // Obtain the password from the Authentication object
		
		try {
			UserDetails user = userDetailsService.loadUserByUsername(username); // Use the UserDetailsService to load the user.
			
			if(passwordEncoder.matches(password, user.getPassword())) { // Use the password encoder to check if the provided password matches with the user password (use the match method)
				return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
			} else {
				throw new BadCredentialsException("Bad Credentials");
			}
			
		} catch (UsernameNotFoundException unfe){
			throw new BadCredentialsException(unfe.getMessage());
		}
	}
	
    @Override
    public boolean supports(Class<?> authentication) {
    	return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
