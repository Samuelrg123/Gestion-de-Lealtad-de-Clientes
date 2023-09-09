package com.example.lealtad.bd.entidad;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "recompensa_beneficio")
@Data
public class Recompensa {
    @Id
    @Column
    private int id_recompensa;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private int puntos_necesarios;
    @Column
    private LocalDate fecha_creacion;
    @Column
    private LocalDate fecha_modificacion;
}

