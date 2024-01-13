# Spring Security Lab Projects

Labs for the Spring Security course

To import these labs into your IDE, import the parent pom `lab/pom.xml` as Maven projects.

# Module 1 & 2: Course Introduction & Introduction to Security

- Role based, attribute based
- OWASP (top 10 risks)
- Hashing and encryption/decryption
- Security misconfiguration
- SSRF (Server-Side Request Forgery)
- By default, spring security enable protection against all these
    - Session FIxation
    - Clickjacking
    - CSRF
    - Cross site scripting

- Declarative Security (using annotations)

---

# Module 3: Spring Security Basics

### DelegatingFilterProxy (slide 67)

- not a spring bean
- acts as a bridge between servlet container life cycle and spring application context

### HTTPFirewall

- StrictHttpFirewall used by default
- rejects requests that appear malicious

### Multiple SecurityFilterChains (slide 71)

- just take note the image

### Overview of Security Context (Slide 73)

- Principal -> represents the user
- Authoriities -> roles (if its RBAC)
- Details -> up to you to customize the user details
- Security context is cleared when another request is cleared
- how internally the filter chain / security context holder works refer to slide 77

### Spring Security Autoconfiguration (slide 82)

(slide 83)

- SecurityAutoConfiguration
- SecurityFilterAutoConfiguration
- UserDetailsServiceAutoConfiguration

### Overriding Defaults (slide 88, 87 is deprecated)

- SecurityConfig 3 main bean methods
    - `filterChain` (customise the 15 filters)
        - Injects HttpSecurity API
        - is the fluent api for http.authorizeRequests()
        - lambda (slide 90), makes configuration more readable and remove the need for and()

    - `authenticationManager` (how you want to authenticate)
    - `webSecurity` (exclude certain like .js, images etc, static resources)

---

# Module 4: AuthenticationManager

- drives authentication
- `ProviderManager` is the main implementation (implements `AuthenticationManager`) which delegates to
  multiple `AuthenticationProvider` (such as JwtAuthenticationProvider, LdapAuthenticationProvider)
- `DaoAuthenticationProvider` perform authentication by delegating to a `UserDetailsService` implementation.

### Requesting Credentials

- `AuthenticationEntryPoint` strategy decides how to request credentials

### Accessing a protected resource

- `FilterSecurityInterceptor` protects the resource and throws access denied exception

---

# Module 5: Securing Web Applications

- note that `AccessDecisionManager` is deprecated

- authority vs role
    - .hasAuthority("ROLE_ADMIN")
    - .hasRole("ADMIN")

```
isAuthenticated()       Returns true if the user is not anonymous
isFullyAuthenticated()  Returns true if the user is not an anonymous or a remember-me user
```

- Refer to SpEL-Based authorization (seems interesting) slide 167
- can do object.getid in SPEL expression

---

# Module 6: Method Security

- need to @EnableMethodSecurity explicitly, SB only enables Web Security by default (at http layer)
- can consider Security Meta-Annotations for update/delete?? (slide 208)

---

# Extras

- EnvironmentPostProcessor
- spring.factories (org.springframework.boot.env.EnvironmentPostProcessor=com.example.demo.MyERP)
- BeanPostProcessor

---

# Module 7: Security Testing

- Spring Security Testing Support (Slide 222)

### MockMvc

- upon configuring the mockmvc, it will be autoconfigured with security filters
- implicit SecurityContext (slide 229)
- MockMvc Configured with security filters (slide 237)
- Testing Basic Authentication (slide 243)
- `exportTestSecurityContext` to "copy" to `SecurityContextHolder`, must make sure to clear, if not next test will have
  the same context

---

# Module 8: Managing Passwords

- @SpyBean to verify if any methods is called in a bean

---

# Module 10: OAuth2 and OIDC Concepts

- OAuth2 concepts (slide 334)
- Scope is a mechanism in OAuth2 to limit an application's access to a user's account. (slide 336 additional)
- Authorization Server: doesnt do authentication.
- Resource Server: keeps your user-info
- Read more on OIDC (slide 338)
- Client in this case is both FE + BE (Slide 345)
- PKCE (Proof-Key for Code Exchange) to prevent interception attack (Slide 348)

---

# Module 11: Role of the Authorization Server


---


# Module 12: Protecting and Accessing Resources with OAuth2

- JWT Authentication flow reference (slide 416)
- 