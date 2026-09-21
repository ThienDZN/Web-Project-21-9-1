package vn.iotstar.example1.security;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.iotstar.example1.dto.UserHeaderView;
import vn.iotstar.example1.entity.AppUser;

public final class Example1Principal implements UserDetails {
    private final String email; private final String password; private final boolean enabled; private final UserHeaderView profile; private final List<GrantedAuthority> authorities;
    public Example1Principal(AppUser user, UserHeaderView profile) { this.email = user.getEmail(); this.password = user.getPassword(); this.enabled = user.isEnabled(); this.profile = profile; this.authorities = List.of(new SimpleGrantedAuthority(user.getRole().getName())); }
    public String getEmail() { return email; }
    public String getFullName() { return profile.fullName(); }
    public String getImageUrl() { return profile.imageUrl(); }
    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }
    @Override public String getPassword() { return password; }
    @Override public String getUsername() { return email; }
    @Override public boolean isEnabled() { return enabled; }
}
