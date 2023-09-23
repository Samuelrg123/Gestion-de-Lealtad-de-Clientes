package com.example.lealtad.bd.entidad;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "recompensa_beneficio")
@Data
public class Recompensa {
    @Id
    @Column(name = "id_recompensa")
    private int idRecompensa;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column(name = "puntos_necesarios")
    private int puntosNecesarios;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;
}

