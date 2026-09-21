package vn.iotstar.example1.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.iotstar.example1.mapper.UserMapper;
import vn.iotstar.example1.repository.AppUserRepository;

@Service
public class Example1UserDetailsService implements UserDetailsService {
    private final AppUserRepository users; private final UserMapper mapper;
    public Example1UserDetailsService(AppUserRepository users, UserMapper mapper) { this.users = users; this.mapper = mapper; }
    @Override @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        return users.findByEmailIgnoreCase(email).map(user -> new Example1Principal(user, mapper.toHeaderView(user))).orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
    }
}
