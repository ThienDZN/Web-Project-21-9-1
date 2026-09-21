package vn.iotstar.example1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.iotstar.example1.entity.AppUser;
import vn.iotstar.example1.entity.Role;
import vn.iotstar.example1.repository.AppUserRepository;
import vn.iotstar.example1.repository.RoleRepository;

@Configuration
public class DataInitializer {
    @Bean CommandLineRunner seed(RoleRepository roles, AppUserRepository users, PasswordEncoder encoder, @Value("${app.demo.password:}") String demoPassword) {
        return args -> { Role role = roles.findByName("ROLE_USER").orElseGet(() -> roles.save(new Role("ROLE_USER")));
            if (!demoPassword.isBlank() && !users.existsByEmailIgnoreCase("demo.example1@test.local")) { AppUser user = new AppUser("demo.example1@test.local", encoder.encode(demoPassword), "Example 1 Demo", role); user.setEnabled(true); users.save(user); }
        };
    }
}
