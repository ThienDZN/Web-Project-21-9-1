package vn.iotstar.example1;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.*;
import vn.iotstar.example1.entity.*;
import vn.iotstar.example1.repository.*;

@SpringBootTest @AutoConfigureMockMvc
class Example1IntegrationTest {
    @Autowired MockMvc mvc; @Autowired RoleRepository roles; @Autowired AppUserRepository users; @Autowired PasswordEncoder encoder;
    @Test void emailLoginShowsUserInHeader() throws Exception {
        Role role = roles.findByName("ROLE_USER").orElseThrow(); AppUser user = new AppUser("student1@example.test", encoder.encode("valid-password"), "Student One", role); user.setEnabled(true); users.save(user);
        MvcResult login = mvc.perform(post("/login").with(csrf()).param("email", "student1@example.test").param("password", "valid-password")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/")).andReturn();
        mvc.perform(get("/").session((MockHttpSession) login.getRequest().getSession(false))).andExpect(status().isOk()).andExpect(content().string(org.hamcrest.Matchers.containsString("Student One"))).andExpect(content().string(org.hamcrest.Matchers.containsString("student1@example.test")));
    }
}
