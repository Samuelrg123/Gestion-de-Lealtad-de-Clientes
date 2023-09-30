package com.example.lealtad.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class DetalleRecompensaDTO {
    private String nombre;
    private String descripcion;
    private int puntosNecesarios;
}
