package com.example.lealtad.logica;

import com.example.lealtad.bd.entidad.Historial_Redencion;
import com.example.lealtad.bd.repository.ClienteRepository;
import com.example.lealtad.bd.repository.HistorialRepository;
import com.example.lealtad.bd.repository.RecompensaRepository;
import com.example.lealtad.controller.dto.HistorialDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class HistorialLogica {
    private HistorialRepository historialRepository;
    private ClienteRepository clienteRepository;
    private RecompensaRepository recompensaRepository;

    public List<Historial_Redencion> obtenerHistorial(HistorialDTO historialDTO) {
        try {
            historialRepository.findAllByCliente(historialDTO.getCedulaCliente());
            return historialRepository.findAllByCliente(historialDTO.getCedulaCliente());
        }catch (IllegalArgumentException e){
            return Collections.emptyList() ;
        }
    }

    public void registrarRecompensaRedimida(HistorialDTO historialDTO) {
        Historial_Redencion historialRedencion = new Historial_Redencion();
        historialRedencion.setCliente(clienteRepository.findById(historialDTO.getCedulaCliente()).get());
        historialRedencion.setRecompensa(recompensaRepository.findById(historialDTO.getIdRecompensa()).get());
        historialRedencion.setFecha_redencion(LocalDate.now());
        historialRedencion.setFecha_creacion(LocalDate.now());
        historialRedencion.setFecha_modificacion(LocalDate.now());
        historialRepository.save(historialRedencion);


    }
}
