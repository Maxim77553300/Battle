package by.battle.authenticationservice.service;

import by.battle.authenticationservice.entity.Role;
import by.battle.authenticationservice.repository.UserRepository;
import by.battle.security.user.AuthUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = userRepository.findByName(username).get().getPassword();
        List<Role> roles = userRepository.findByName(username).get().getRoles();
        String userId = userRepository.findByName(username).get().getId();
        Collection<? extends GrantedAuthority> authorities =
                roles
                        .stream()
                        .map(it -> it.getName())
                        .map(r -> new SimpleGrantedAuthority(r.name()))
                        .collect(Collectors.toList());
        return new AuthUser(username, password, authorities, userId);
    }
}
