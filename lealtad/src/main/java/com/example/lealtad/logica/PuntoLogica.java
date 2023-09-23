package com.example.lealtad.logica;

import com.example.lealtad.bd.repository.PuntoRepository;
import com.example.lealtad.controller.dto.PuntoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PuntoLogica {
    private PuntoRepository puntoRepository;

    public double obtenerPuntosActuales(PuntoDTO puntoDTO) {
        return puntoRepository.findPuntosAcumuladosByCliente(puntoDTO.getCliente());
    }

    public void actualizarPuntos(PuntoDTO puntoDTO) {
        puntoRepository.updatePuntosetPuntosAcumulados(puntoDTO.getCliente(), puntoDTO.getPuntosAcumulados());
    }
}
