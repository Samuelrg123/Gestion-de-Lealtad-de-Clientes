package com.example.lealtad.bd.repository;

import com.example.lealtad.bd.entidad.Historial_Redencion;
import com.example.lealtad.bd.entidad.Recompensa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistorialRepository extends JpaRepository<Historial_Redencion,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM historial_redencion WHERE ID_cliente = :cedulaCliente")
        List<Historial_Redencion> findAllByCliente(@Param("cedulaCliente")int cedulaCliente);
}
