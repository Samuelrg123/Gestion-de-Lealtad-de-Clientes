package com.example.lealtad.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecompensaEntidadDTO {
    private int idRecompensa;
    private String nombre;
    private String descripcion;
    private int puntosNecesarios;
}
