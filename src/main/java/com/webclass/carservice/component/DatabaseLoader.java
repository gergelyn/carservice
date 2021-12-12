package com.webclass.carservice.component;

import com.webclass.carservice.entity.Client;
import com.webclass.carservice.entity.Manager;
import com.webclass.carservice.repository.ClientRepository;
import com.webclass.carservice.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {
    private final ClientRepository clients;
    private final ManagerRepository managers;

    @Autowired
    public DatabaseLoader(ClientRepository clientRepository, ManagerRepository managerRepository) {
        this.clients = clientRepository;
        this.managers = managerRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Manager bob = this.managers.save(new Manager("Bob", "Bobby",  "info@bobbobby.com", "secret", "ROLE_MANAGER"));
        SecurityContextHolder
                .getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken("info@bobbobby.com", "doesn't matter", AuthorityUtils.createAuthorityList("ROLE_MANAGER")));
        this.clients.save(new Client("John", "Doe", "Audi", "ABC-111", "problem", bob));
        this.clients.save(new Client("Jane", "Doe", "BMW", "ABC-123", "problem", bob));
        SecurityContextHolder.clearContext();
    }
}
