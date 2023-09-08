package com.example.lealtad.controller.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
@Data
public class ClienteDTO {
    private int cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private int telefono;
}
