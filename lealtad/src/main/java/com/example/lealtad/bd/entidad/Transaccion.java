package com.example.lealtad.bd.entidad;

import com.example.lealtad.bd.entidad.Cliente;
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
    @JoinColumn(name = "ID_cliente",referencedColumnName = "cedula")
    private Cliente cliente;

    @Column
    private LocalDate fecha_transaccion;

    @Column
    private int monto;

    @Column
    private double puntos_generados;

    @Column
    private LocalDate fecha_creacion;

    @Column
    private LocalDate fecha_modificacion;
}
