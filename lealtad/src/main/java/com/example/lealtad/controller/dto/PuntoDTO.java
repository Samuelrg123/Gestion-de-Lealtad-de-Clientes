package com.example.lealtad.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class PuntoDTO {
    @NonNull
    private int cliente;
    private double puntosAcumulados;
}