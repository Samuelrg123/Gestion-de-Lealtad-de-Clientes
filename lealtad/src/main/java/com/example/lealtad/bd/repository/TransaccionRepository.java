package com.example.lealtad.bd.repository;

import com.example.lealtad.bd.entidad.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion,Integer> {
}
