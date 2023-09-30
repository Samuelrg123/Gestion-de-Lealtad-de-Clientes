package com.example.lealtad.controller;

import com.example.lealtad.controller.dto.DetalleRecompensaDTO;
import com.example.lealtad.controller.dto.RecompensaDTO;
import com.example.lealtad.controller.dto.RecompensaEntidadDTO;
import com.example.lealtad.controller.dto.RespuestaDTO;
import com.example.lealtad.logica.RecompensaLogica;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ControladorRecompensa {
    private RecompensaLogica recompensaLogica;

    @PostMapping("/recompensa/guardar")
    public RespuestaDTO guardarRecompensa(@RequestBody RecompensaEntidadDTO recompensaEntidadDTO) {
        recompensaLogica.guardarRecompensa(recompensaEntidadDTO);
        return new RespuestaDTO("Recompensa registrado correctamente");
    }

    @GetMapping("/recompensas")
    public List<DetalleRecompensaDTO> buscarRecompensas() {
        return recompensaLogica.buscarRecompensas();
    }

    @GetMapping("/recompensas/{cedula}")
    public List<DetalleRecompensaDTO> buscarRecompensasPorCliente(@PathVariable int cedula) {
        RecompensaDTO recompensaDTO = new RecompensaDTO();
        recompensaDTO.setCedula(cedula);
        return recompensaLogica.buscarRecompensasPorCliente(recompensaDTO);
    }

    @PostMapping("/redimir-recompensa")
    public RespuestaDTO redimirRecompensa(@RequestBody RecompensaDTO recompensaDTO) {
        return recompensaLogica.redimirRecompensa(recompensaDTO);
    }
}
