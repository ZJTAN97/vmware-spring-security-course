package rewardsdining.security;

import java.time.Duration;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptStrengthTester {
	
	private static final Logger logger = LoggerFactory.getLogger(BCryptStrengthTester.class);

	
	private static final int TARGET_HASHING_TIME = 1000;
	
	private static final int MIN_strength = 4;
	
	private static final int MAX_strength = 31;

	private static final String PASSWORD = "s3cureP4ssword#";
	
	
	public static int startTest() {
		int strength = MIN_strength - 1;
		Duration timeElapsed = Duration.ZERO;
		
		while(++strength < MAX_strength && timeElapsed.toMillis() < TARGET_HASHING_TIME) {
			var bcrypt = new BCryptPasswordEncoder(strength);
			
			Instant start = Instant.now();
			bcrypt.encode(PASSWORD);
			timeElapsed = Duration.between(start, Instant.now()); 
			
			logger.info("BCrypt with strength {} took {} millis", strength, timeElapsed.toMillis());
		}
		
		return strength;
	}
}
