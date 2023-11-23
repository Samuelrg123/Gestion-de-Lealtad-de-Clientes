package com.example.lealtad.logica;

import com.example.lealtad.bd.entidad.Recompensa;
import com.example.lealtad.bd.repository.RecompensaRepository;
import com.example.lealtad.controller.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecompensaLogica {
    private RecompensaRepository recompensaRepository;
    private PuntoLogica puntoLogica;
    private HistorialLogica historialLogica;

    public List<DetalleRecompensaDTO> buscarRecompensasPorCliente(RecompensaDTO recompensaDTO) {
        PuntoDTO puntoDTO = new PuntoDTO();
        puntoDTO.setCliente(recompensaDTO.getCedula());
        double puntosCliente = puntoLogica.obtenerPuntosActuales(puntoDTO);
        return recompensaRepository.findAllInfoRecompensasByPuntosCliente(puntosCliente).stream().map(recompensa -> new DetalleRecompensaDTO(recompensa.getIdRecompensa(), recompensa.getNombre(), recompensa.getDescripcion(), recompensa.getPuntosNecesarios())).collect(Collectors.toList());

    }

    public List<DetalleRecompensaDTO> buscarRecompensas() {
        return recompensaRepository.findAll().stream().map(recompensa -> new DetalleRecompensaDTO(recompensa.getIdRecompensa(),recompensa.getNombre(), recompensa.getDescripcion(), recompensa.getPuntosNecesarios())).collect(Collectors.toList());
    }

    public RespuestaDTO redimirRecompensa(RecompensaDTO recompensaDTO) {
        PuntoDTO puntoDTO = new PuntoDTO();
        puntoDTO.setCliente(recompensaDTO.getCedula());
        double puntosCliente = puntoLogica.obtenerPuntosActuales(puntoDTO);
        Recompensa recompensa = recompensaRepository.findById(recompensaDTO.getIdRecompensa()).get();
        int puntosRecompensa = recompensa.getPuntosNecesarios();
        if (puntosRecompensa <= puntosCliente) {
            historialLogica.registrarRecompensaRedimida(new HistorialDTO(recompensaDTO.getCedula(), recompensaDTO.getIdRecompensa()));
            double puntosActualizados = puntosCliente - puntosRecompensa;
            puntoLogica.actualizarPuntos(new PuntoDTO(recompensaDTO.getCedula(), puntosActualizados));
            return new RespuestaDTO("Recompensa redimida y registrada con exito. Se han descontado los puntos al cliente");
        } else {
            return new RespuestaDTO("No cumple con la cantidad de puntos para redimir esta recompensa. revise Recompensas Por Cliente e ingrese la cedula: " + recompensaDTO.getCedula() + " para conocer las recompensas a las que puede acceder actualmente");
        }
    }

    public void guardarRecompensa(RecompensaEntidadDTO recompensaEntidadDTO) {
        Recompensa recompensa = new Recompensa();
        recompensa.setIdRecompensa(recompensaEntidadDTO.getIdRecompensa());
        recompensa.setNombre(recompensaEntidadDTO.getNombre());
        recompensa.setDescripcion(recompensaEntidadDTO.getDescripcion());
        recompensa.setPuntosNecesarios(recompensaEntidadDTO.getPuntosNecesarios());
        recompensa.setFechaCreacion(LocalDate.now());
        recompensa.setFechaModificacion(LocalDate.now());

        recompensaRepository.save(recompensa);
    }
}
