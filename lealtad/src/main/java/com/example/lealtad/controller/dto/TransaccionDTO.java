package com.example.lealtad.controller.dto;

import com.example.lealtad.bd.entidad.Cliente;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransaccionDTO {
    private int cliente;
    private LocalDate fecha_transaccion;
    private int monto;
}
