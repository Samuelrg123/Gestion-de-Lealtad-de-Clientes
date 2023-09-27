package com.example.lealtad.logica;

import com.example.lealtad.bd.entidad.Cliente;
import com.example.lealtad.bd.entidad.HistorialRedencion;
import com.example.lealtad.bd.entidad.Recompensa;
import com.example.lealtad.bd.repository.ClienteRepository;
import com.example.lealtad.bd.repository.HistorialRepository;
import com.example.lealtad.bd.repository.RecompensaRepository;
import com.example.lealtad.controller.dto.HistorialDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HistorialLogicaTest {
    @Mock
    private HistorialRepository historialRepository;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private RecompensaRepository recompensaRepository;
    @InjectMocks
    HistorialLogica historialLogica;

    @Test
    void obtenerHistorial() {
    }

    @Test
    void Dado_cliente_id_1_recompensa_id_1_Cuando_registrarRecompensaRedimida_Entonces_guardar_historialRedencion() {
        Cliente cliente = new Cliente();
        cliente.setCedula(1);
        cliente.setNombre("Prueba");
        cliente.setApellido("Unitaria");
        cliente.setCorreo("pruebas@pruebas.com");
        cliente.setTelefono(1234);
        cliente.setFechaCreacion(LocalDate.now());
        cliente.setFechaModificacion(LocalDate.now());

        Recompensa recompensa = new Recompensa();
        recompensa.setIdRecompensa(1);
        recompensa.setNombre("recompensaPrueba");
        recompensa.setDescripcion("Esto es una recompensa de prueba");
        recompensa.setPuntosNecesarios(2);
        recompensa.setFechaCreacion(LocalDate.now());
        recompensa.setFechaModificacion(LocalDate.now());

        HistorialRedencion historialRedencion = new HistorialRedencion();
        historialRedencion.setCliente(cliente);
        historialRedencion.setRecompensa(recompensa);
        historialRedencion.setFechaRedencion(LocalDate.now());
        historialRedencion.setFechaCreacion(LocalDate.now());
        historialRedencion.setFechaModificacion(LocalDate.now());

        HistorialDTO historialDTO = new HistorialDTO(1, 1);

        when(clienteRepository.findById(historialDTO.getCedulaCliente())).thenReturn(Optional.of(cliente));
        when(recompensaRepository.findById(historialDTO.getIdRecompensa())).thenReturn(Optional.of(recompensa));

        historialLogica.registrarRecompensaRedimida(historialDTO);
        Mockito.verify(historialRepository).save(historialRedencion);
    }
}