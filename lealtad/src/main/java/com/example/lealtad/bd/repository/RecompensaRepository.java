package com.example.lealtad.bd.repository;

import com.example.lealtad.bd.entidad.Recompensa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import java.util.List;

public interface RecompensaRepository extends JpaRepository<Recompensa,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM recompensa_beneficio WHERE puntos_necesarios <= :puntosCliente")
    List<Recompensa> findAllInfoRecompensasByPuntosCliente(@Param("puntosCliente")double puntosCliente);
}
