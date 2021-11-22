package com.webclass.carservice.repository;

import com.webclass.carservice.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByProgress(String progress);
}
