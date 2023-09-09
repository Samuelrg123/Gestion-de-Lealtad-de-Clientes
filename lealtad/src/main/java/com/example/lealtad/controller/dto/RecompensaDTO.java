package com.example.lealtad.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class RecompensaDTO {
    @NonNull
    private int cedula;

    private int id_recompensa;
}
