package com.example.lealtad.bd.entidad;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "historial_redencion")
@Data
public class HistorialRedencion {
    @Id
    @Column(name = "ID_redencion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRedencion;

    @ManyToOne
    @JoinColumn(name = "ID_cliente", referencedColumnName = "cedula")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "ID_recompensa", referencedColumnName = "id_recompensa")
    private Recompensa recompensa;

    @Column(name = "fecha_redencion")
    private LocalDate fechaRedencion;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;
}
