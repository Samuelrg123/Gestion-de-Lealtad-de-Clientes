package com.example.lealtad.controller.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PuntoDTO {
    private int cliente;
    private double puntosAcumulados;
}