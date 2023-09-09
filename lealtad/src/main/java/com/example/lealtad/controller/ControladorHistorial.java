package com.example.lealtad.controller;

import com.example.lealtad.bd.entidad.Historial_Redencion;
import com.example.lealtad.controller.dto.HistorialDTO;
import com.example.lealtad.logica.HistorialLogica;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor

public class ControladorHistorial {
    private HistorialLogica historialLogica;
    @GetMapping("/historial-redenciones")
    public List<Historial_Redencion> revisarHistorialRecompensas(@RequestParam int cedula){
        return historialLogica.obtenerHistorial(new HistorialDTO(cedula));
    }
}
