package com.example.lealtad.bd.entidad;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table
@Data
public class Cliente {
    @Id
    @Column
    private int cedula;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String correo;

    @Column
    private int telefono;

    @Column
    private LocalDate fecha_creacion;

    @Column
    private LocalDate fecha_modificacion;
}
