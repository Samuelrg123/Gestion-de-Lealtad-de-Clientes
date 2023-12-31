package com.example.lealtad.bd.entidad;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
public class Transaccion {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTransaccion;

    @ManyToOne
    @JoinColumn(name = "ID_cliente", referencedColumnName = "cedula")
    private Cliente cliente;

    @Column(name = "fecha_transaccion")
    private LocalDate fechaTransaccion;

    @Column
    private int monto;

    @Column(name = "puntos_generados")
    private double puntosGenerados;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;
}
