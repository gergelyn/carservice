package com.webclass.carservice.repository;

import com.webclass.carservice.entity.Manager;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ManagerRepository extends Repository<Manager, Long> {
    Manager save(Manager manager);
    Manager findByEmail(String email);
}
