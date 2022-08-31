package com.c6bank.cliente.Repository;

import ch.qos.logback.core.net.server.Client;
import com.c6bank.cliente.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByEmailAndSenha(String email, String senha);

    @Query(value = "select c from Cliente c")
    List<Cliente> findAllClientes();

    @Query(value = "SELECT c FROM Cliente c WHERE c.ativo = ?1")
    List<Cliente> findAllClientesInativo(Boolean ativo);
}
