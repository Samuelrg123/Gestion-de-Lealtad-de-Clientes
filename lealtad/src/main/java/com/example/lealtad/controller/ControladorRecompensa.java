package com.example.lealtad.controller;

import com.example.lealtad.bd.entidad.Recompensa;
import com.example.lealtad.controller.dto.DetalleRecompensaDTO;
import com.example.lealtad.controller.dto.RecompensaDTO;
import com.example.lealtad.controller.dto.RespuestaDTO;
import com.example.lealtad.logica.RecompensaLogica;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ControladorRecompensa {
    private RecompensaLogica recompensaLogica;

    @GetMapping("/recompensas/{cedula}")
    public List<DetalleRecompensaDTO> buscarRecompensasPorCliente(@PathVariable int cedula) {
        return recompensaLogica.buscarRecompensasPorCliente(new RecompensaDTO(cedula));
    }

    @GetMapping("/recompensas")
    public List<DetalleRecompensaDTO> buscarRecompensas() {
        return recompensaLogica.buscarRecompensas();
    }

    @PostMapping("/redimir-recompensa")
    public RespuestaDTO redimirRecompensa(@RequestBody RecompensaDTO recompensaDTO) {
        return recompensaLogica.redimirRecompensa(recompensaDTO);
    }
}
