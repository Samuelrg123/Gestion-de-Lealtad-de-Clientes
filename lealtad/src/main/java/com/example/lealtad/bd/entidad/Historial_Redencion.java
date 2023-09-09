package com.example.lealtad.bd.entidad;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "historial_redencion")
@Data
public class Historial_Redencion {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_redencion;
    @ManyToOne
    @JoinColumn(name = "ID_cliente",referencedColumnName = "cedula")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "ID_recompensa",referencedColumnName = "id_recompensa")
    private Recompensa recompensa;
    @Column
    private LocalDate fecha_redencion;
    @Column
    private LocalDate fecha_creacion;
    @Column
    private LocalDate fecha_modificacion;
}
