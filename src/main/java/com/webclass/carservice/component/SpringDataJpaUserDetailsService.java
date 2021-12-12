package com.webclass.carservice.component;

import com.webclass.carservice.entity.Manager;
import com.webclass.carservice.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SpringDataJpaUserDetailsService implements UserDetailsService {
    private final ManagerRepository repository;

    @Autowired
    public SpringDataJpaUserDetailsService(ManagerRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Manager manager = this.repository.findByEmail(email);
        return new User(manager.getEmail(), manager.getPassword(), AuthorityUtils.createAuthorityList(manager.getRoles()));
    }
}
