package com.example.lealtad.bd.entidad;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
public class Punto {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPuntos;

    @OneToOne
    @JoinColumn(name = "ID_cliente",referencedColumnName = "cedula")
    private Cliente cliente;

    @Column
    private double puntos_acumulados;

    @Column
    private LocalDate fecha_creacion;

    @Column
    private LocalDate fecha_acumulacion;
}
