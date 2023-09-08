package com.example.lealtad.bd.repository;

import com.example.lealtad.bd.entidad.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

}
