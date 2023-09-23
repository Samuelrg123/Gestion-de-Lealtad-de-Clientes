package com.example.lealtad.logica;

import com.example.lealtad.bd.entidad.Recompensa;
import com.example.lealtad.bd.repository.RecompensaRepository;
import com.example.lealtad.controller.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecompensaLogica {
    private RecompensaRepository recompensaRepository;
    private PuntoLogica puntoLogica;
    private HistorialLogica historialLogica;

    public List<DetalleRecompensaDTO> buscarRecompensasPorCliente(RecompensaDTO recompensaDTO) {
        double puntosCliente = puntoLogica.obtenerPuntosActuales(new PuntoDTO(recompensaDTO.getCedula()));
        return recompensaRepository.findAllInfoRecompensasByPuntosCliente(puntosCliente).stream()
                .map(recompensa -> new DetalleRecompensaDTO(recompensa.getNombre(), recompensa.getDescripcion(), recompensa.getPuntosNecesarios())).collect(Collectors.toList());

    }

    public List<DetalleRecompensaDTO> buscarRecompensas() {
        return recompensaRepository.findAll()
                .stream()
                .map(recompensa -> new DetalleRecompensaDTO(recompensa.getNombre(), recompensa.getDescripcion(), recompensa.getPuntosNecesarios())).collect(Collectors.toList());
    }

    public RespuestaDTO redimirRecompensa(RecompensaDTO recompensaDTO) {
        double puntosCliente = puntoLogica.obtenerPuntosActuales(new PuntoDTO(recompensaDTO.getCedula()));
        Recompensa recompensa = recompensaRepository.findById(recompensaDTO.getIdRecompensa()).get();
        int puntosRecompensa = recompensa.getPuntosNecesarios();
        if (puntosRecompensa <= puntosCliente) {
            historialLogica.registrarRecompensaRedimida(new HistorialDTO(recompensaDTO.getCedula(), recompensaDTO.getIdRecompensa()));
            double puntosActualizados = puntosCliente - puntosRecompensa;
            puntoLogica.actualizarPuntos(new PuntoDTO(recompensaDTO.getCedula(), puntosActualizados));
            return new RespuestaDTO("Recompensa redimida y registrada con exito. Se han descontado los puntos al cliente");
        } else {
            return new RespuestaDTO("No cumple con la cantidad de puntos para redimir esta recompensa. revise http://localhost:8080/recompensas/" + recompensaDTO.getCedula() + " para conocer las recompensas a las que puede acceder actualmente");
        }
    }
}
