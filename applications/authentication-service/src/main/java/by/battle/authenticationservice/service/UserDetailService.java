package by.battle.authenticationservice.service;

import by.battle.authenticationservice.repository.UserRepository;
import by.battle.security.user.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = userRepository.findByName(username).get().getPassword();
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("write", "read");
        String userId = userRepository.findByName(username).get().getId();
        return new AuthUser(username, password, authorities, userId);
    }
}
