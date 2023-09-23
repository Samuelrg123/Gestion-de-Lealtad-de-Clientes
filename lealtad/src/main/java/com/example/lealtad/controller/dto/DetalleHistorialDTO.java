package com.example.lealtad.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class DetalleHistorialDTO {
    private int idRedencion;
    private String nombreCliente;
    private String apellidoCliente;
    private int recompensaId;
    private String nombreRecompensa;
    private String descripcionRecompensa;
    private int puntosNecesariosRecompensa;
    private LocalDate fechaRedencionRecompensa;
}
