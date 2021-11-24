package com.webclass.carservice.repository;

import com.webclass.carservice.entity.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
    List<Client> findByProgress(String progress);
}
