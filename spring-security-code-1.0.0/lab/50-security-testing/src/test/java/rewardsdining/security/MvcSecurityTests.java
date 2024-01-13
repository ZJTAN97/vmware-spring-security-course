package rewardsdining.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class MvcSecurityTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSuccessfulFormLogin() throws Exception {

        mockMvc.perform(formLogin().user("keith").password("spring"))
                .andExpect(authenticated().withUsername("keith").withRoles("USER"));
    }


    @Test
    void testSuccessfulBasicAuthentication() throws Exception {
        mockMvc.perform(get("/").with(user("keith").password("spring").roles("USER")))
                .andExpect(authenticated().withRoles("USER"));
    }


}
		