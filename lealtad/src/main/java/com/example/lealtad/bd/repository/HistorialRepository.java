package com.example.lealtad.bd.repository;

import com.example.lealtad.bd.entidad.HistorialRedencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistorialRepository extends JpaRepository<HistorialRedencion, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM historial_redencion WHERE ID_cliente = :cedulaCliente")
    List<HistorialRedencion> findAllByCliente(@Param("cedulaCliente") int cedulaCliente);
}
