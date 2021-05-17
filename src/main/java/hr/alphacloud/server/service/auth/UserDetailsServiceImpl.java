package hr.alphacloud.server.service.auth;

import hr.alphacloud.server.model.entity.User;
import hr.alphacloud.server.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        final Optional<User> userOptional = userRepository.findByEmail(email);
        return resolveUserDetails(email, userOptional);
    }

    public UserDetails loadUserByUsernameAndToken(String email, String jwtToken) {
        final Optional<User> userOptional = userRepository.findByEmail(email);
        return resolveUserDetails(email, userOptional);
    }

    private UserDetails resolveUserDetails(String email, Optional<User> userOptional) {
        return userOptional.map(user -> new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

                return authorities;
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getEmail();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        }).orElseGet(() -> {
            throw new UsernameNotFoundException("User not found with email: " + email);
        });
    }
}
