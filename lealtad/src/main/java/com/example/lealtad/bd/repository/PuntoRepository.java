package com.example.lealtad.bd.repository;

import com.example.lealtad.bd.entidad.Punto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PuntoRepository extends JpaRepository<Punto,Integer> {
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value= "UPDATE punto SET puntos_acumulados = :puntosAcumulados WHERE ID_cliente= :cedulaCliente",nativeQuery = true)
    void updatePuntosetPuntosAcumulados(@Param("cedulaCliente") int cedulaCliente,@Param("puntosAcumulados") double puntosAcumulados);


    @Query(value= "SELECT puntos_acumulados FROM punto WHERE ID_cliente = :cedulaCliente",nativeQuery = true)
    double findPuntosAcumuladosByCliente(@Param("cedulaCliente") int cedulaCliente);

}
