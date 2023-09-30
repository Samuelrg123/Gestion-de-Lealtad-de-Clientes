package com.example.lealtad.unit.logica;

import com.example.lealtad.bd.repository.PuntoRepository;
import com.example.lealtad.controller.dto.PuntoDTO;
import com.example.lealtad.logica.PuntoLogica;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PuntoLogicaTest {
    @Mock
    private PuntoRepository puntoRepository;
    @InjectMocks
    PuntoLogica puntoLogica;

    @Test
    void Dado_cliente_con_puntos_2_Cuando_findPuntosAcumuladosByCliente_Entonces_puntosActuales_2() {
        PuntoDTO puntoDTO = new PuntoDTO();
        puntoDTO.setCliente(1);
        double puntos = 2.0;

        when(puntoRepository.findPuntosAcumuladosByCliente(1)).thenReturn(puntos);
        double puntosActuales = puntoLogica.obtenerPuntosActuales(puntoDTO);

        assertEquals(2, puntosActuales);
    }

    @Test
    void Dado_cliente_con_puntosAcumulados_2_Cuando_actualizarPuntos_Entonces_updatePuntossetPuntosAcumulados() {
        PuntoDTO puntoDTO = new PuntoDTO();
        puntoDTO.setCliente(1);
        puntoDTO.setPuntosAcumulados(2);

        doNothing().when(puntoRepository).updatePuntosetPuntosAcumulados(anyInt(), anyDouble());
        puntoLogica.actualizarPuntos(puntoDTO);
        Mockito.verify(puntoRepository).updatePuntosetPuntosAcumulados(puntoDTO.getCliente(), puntoDTO.getPuntosAcumulados());
    }
}