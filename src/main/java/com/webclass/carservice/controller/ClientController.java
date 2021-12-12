package com.webclass.carservice.controller;

import com.webclass.carservice.entity.Client;
import com.webclass.carservice.entity.Manager;
import com.webclass.carservice.repository.ClientRepository;
import com.webclass.carservice.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ManagerRepository managerRepository;

    @GetMapping("/api/clients")
    public Iterable<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @PostMapping("/api/clients")
    public Client createClient(@RequestBody Client newClient) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Manager manager = this.managerRepository.findByEmail(email);
        newClient.setManager(manager);
        return clientRepository.save(newClient);
    }

    @GetMapping("/api/clients/{id}")
    public Client getClient(@PathVariable Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @PutMapping("/api/clients/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client newClient) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setFirstName(newClient.getFirstName());
                    client.setLastName(newClient.getLastName());
                    client.setCarType(newClient.getCarType());
                    client.setLicenseCode(newClient.getLicenseCode());
                    client.setIssue(newClient.getIssue());
                    client.setProgress(newClient.getProgress());
                    String email = SecurityContextHolder.getContext().getAuthentication().getName();
                    Manager manager = this.managerRepository.findByEmail(email);
                    client.setManager(manager);
                    return clientRepository.save(client);
                })
                .orElseGet(() -> {
                    newClient.setId(id);
                    return clientRepository.save(newClient);
                });
    }

    @DeleteMapping("/api/clients/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
    }
}
