package com.example.lealtad.logica;

import com.example.lealtad.bd.entidad.HistorialRedencion;
import com.example.lealtad.bd.repository.ClienteRepository;
import com.example.lealtad.bd.repository.HistorialRepository;
import com.example.lealtad.bd.repository.RecompensaRepository;
import com.example.lealtad.controller.dto.DetalleHistorialDTO;
import com.example.lealtad.controller.dto.HistorialDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HistorialLogica {
    private HistorialRepository historialRepository;
    private ClienteRepository clienteRepository;
    private RecompensaRepository recompensaRepository;

    public List<DetalleHistorialDTO> obtenerHistorial(HistorialDTO historialDTO) {
        try {
            historialRepository.findAllByCliente(historialDTO.getCedulaCliente());
            return historialRepository.findAllByCliente(historialDTO.getCedulaCliente())
                    .stream()
                    .map(historialRedencion -> new DetalleHistorialDTO(historialRedencion.getIdRedencion(), historialRedencion.getCliente().getNombre(),
                            historialRedencion.getCliente().getApellido(), historialRedencion.getRecompensa().getIdRecompensa(), historialRedencion.getRecompensa().getNombre(),
                            historialRedencion.getRecompensa().getDescripcion(), historialRedencion.getRecompensa().getPuntosNecesarios(), historialRedencion.getFechaRedencion())).collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            return Collections.emptyList();
        }
    }

    public void registrarRecompensaRedimida(HistorialDTO historialDTO) {
        HistorialRedencion historialRedencion = new HistorialRedencion();
        historialRedencion.setCliente(clienteRepository.findById(historialDTO.getCedulaCliente()).get());
        historialRedencion.setRecompensa(recompensaRepository.findById(historialDTO.getIdRecompensa()).get());
        historialRedencion.setFechaRedencion(LocalDate.now());
        historialRedencion.setFechaCreacion(LocalDate.now());
        historialRedencion.setFechaModificacion(LocalDate.now());
        historialRepository.save(historialRedencion);
    }
}
