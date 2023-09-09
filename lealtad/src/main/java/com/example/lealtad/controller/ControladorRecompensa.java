package com.example.lealtad.controller;

import com.example.lealtad.bd.entidad.Recompensa;
import com.example.lealtad.controller.dto.RecompensaDTO;
import com.example.lealtad.controller.dto.RespuestaDTO;
import com.example.lealtad.logica.RecompensaLogica;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Data
public class ControladorRecompensa {
    private RecompensaLogica recompensaLogica;

    @GetMapping("/recompensas/{cedula}")
    public List<Recompensa> buscarRecompensasPorCliente(@PathVariable int cedula){
        return recompensaLogica.buscarRecompensasPorCliente(new RecompensaDTO(cedula));
    }

    @GetMapping("/recompensas")
    public List<Recompensa> buscarRecompensas() {
        return recompensaLogica.buscarRecompensas();
    }
    @PostMapping("/redimir-recompensa")
    public RespuestaDTO redimirRecompensa(@RequestBody RecompensaDTO recompensaDTO){
        return recompensaLogica.redimirRecompensa(recompensaDTO);
    }
}
