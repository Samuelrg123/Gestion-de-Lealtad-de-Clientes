package com.example.lealtad.bd.entidad;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
public class Punto {
    @Id
    @Column(name = "ID_puntos")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPuntos;

    @OneToOne
    @JoinColumn(name = "ID_cliente", referencedColumnName = "cedula")
    private Cliente cliente;

    @Column(name = "puntos_acumulados")
    private double puntosAcumulados;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name = "fecha_acumulacion")
    private LocalDate fechaAcumulacion;
}
