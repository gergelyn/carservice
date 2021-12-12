package com.webclass.carservice.repository;

import com.webclass.carservice.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_MANAGER')")
public interface ClientRepository extends CrudRepository<Client, Long> {
    @Override
    @PreAuthorize("#client?.manager == null or #client?.manager?.email == authentication?.name")
    Client save(@Param("client") Client client);

    @Override
    @PreAuthorize("@clientRepository.findById(#id).get().manager.email == authentication?.name")
    void deleteById(@Param("id") Long id);

    @Override
    @PreAuthorize("#client?.manager?.email == authentication?.name")
    void delete(@Param("client") Client client);
}
