package com.example.lealtad.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class HistorialDTO {
    @NonNull
    private int cedulaCliente;

    private int idRecompensa;
}
