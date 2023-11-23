package com.example.lealtad.controller;

import com.example.lealtad.controller.dto.DetalleHistorialDTO;
import com.example.lealtad.controller.dto.HistorialDTO;
import com.example.lealtad.logica.HistorialLogica;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@AllArgsConstructor
public class ControladorHistorial {
    private HistorialLogica historialLogica;

    @GetMapping("/historial-redenciones")
    public List<DetalleHistorialDTO> revisarHistorialRecompensas(@RequestParam int cedula) {
        HistorialDTO historialDTO = new HistorialDTO();
        historialDTO.setCedulaCliente(cedula);
        return historialLogica.obtenerHistorial(historialDTO);
    }
}
