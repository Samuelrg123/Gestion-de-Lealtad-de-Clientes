package com.example.lealtad.unit.logica;

import com.example.lealtad.bd.entidad.Cliente;
import com.example.lealtad.bd.entidad.HistorialRedencion;
import com.example.lealtad.bd.entidad.Recompensa;
import com.example.lealtad.bd.repository.ClienteRepository;
import com.example.lealtad.bd.repository.HistorialRepository;
import com.example.lealtad.bd.repository.RecompensaRepository;
import com.example.lealtad.controller.dto.DetalleHistorialDTO;
import com.example.lealtad.controller.dto.HistorialDTO;
import com.example.lealtad.logica.HistorialLogica;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    void Dado_historialDTO_de_cliente_id_1_con2RecompensaRedimidas_Cuando_obtenerHistorial_Entonces_obtener_lista_DetalleHistorialDTO_con2RecompensasEnHistorial() {
        HistorialDTO historialDTO = new HistorialDTO();
        historialDTO.setCedulaCliente(1);

        Cliente cliente = new Cliente();
        cliente.setCedula(1);
        cliente.setNombre("Prueba");
        cliente.setApellido("Prueba");
        cliente.setCorreo("Prueba@prueba.com");
        cliente.setTelefono(1234);
        cliente.setFechaCreacion(LocalDate.now());
        cliente.setFechaModificacion(LocalDate.now());

        Recompensa recompensa_1 = new Recompensa();
        recompensa_1.setIdRecompensa(1);
        recompensa_1.setNombre("RecompensaPrueba1");
        recompensa_1.setDescripcion("Recompensa Prueba 1");
        recompensa_1.setPuntosNecesarios(2);
        recompensa_1.setFechaCreacion(LocalDate.now());
        recompensa_1.setFechaModificacion(LocalDate.now());

        Recompensa recompensa_2 = new Recompensa();
        recompensa_2.setIdRecompensa(2);
        recompensa_2.setNombre("RecompensaPrueba2");
        recompensa_2.setDescripcion("Recompensa Prueba 2");
        recompensa_2.setPuntosNecesarios(5);
        recompensa_2.setFechaCreacion(LocalDate.now());
        recompensa_2.setFechaModificacion(LocalDate.now());

        HistorialRedencion historialRedencion_1 = new HistorialRedencion();
        historialRedencion_1.setIdRedencion(1);
        historialRedencion_1.setCliente(cliente);
        historialRedencion_1.setRecompensa(recompensa_1);
        historialRedencion_1.setFechaRedencion(LocalDate.now());
        historialRedencion_1.setFechaCreacion(LocalDate.now());
        historialRedencion_1.setFechaModificacion(LocalDate.now());

        HistorialRedencion historialRedencion_2 = new HistorialRedencion();
        historialRedencion_2.setIdRedencion(2);
        historialRedencion_2.setCliente(cliente);
        historialRedencion_2.setRecompensa(recompensa_2);
        historialRedencion_2.setFechaRedencion(LocalDate.now());
        historialRedencion_2.setFechaCreacion(LocalDate.now());
        historialRedencion_2.setFechaModificacion(LocalDate.now());


        List<HistorialRedencion> historialRedencionEntityList = new ArrayList<>();
        historialRedencionEntityList.add(historialRedencion_1);
        historialRedencionEntityList.add(historialRedencion_2);

        DetalleHistorialDTO detalleHistorialDTO_1 = new DetalleHistorialDTO(historialRedencion_1.getIdRedencion(), cliente.getNombre(), cliente.getApellido(), recompensa_1.getIdRecompensa(), recompensa_1.getNombre(), recompensa_1.getDescripcion(), recompensa_1.getPuntosNecesarios(), historialRedencion_1.getFechaRedencion());
        DetalleHistorialDTO detalleHistorialDTO_2 = new DetalleHistorialDTO(historialRedencion_2.getIdRedencion(), cliente.getNombre(), cliente.getApellido(), recompensa_2.getIdRecompensa(), recompensa_2.getNombre(), recompensa_2.getDescripcion(), recompensa_2.getPuntosNecesarios(), historialRedencion_2.getFechaRedencion());

        List<DetalleHistorialDTO> historialPruebaEsperado = new ArrayList<>();
        historialPruebaEsperado.add(detalleHistorialDTO_1);
        historialPruebaEsperado.add(detalleHistorialDTO_2);

        when(historialRepository.findAllByCliente(1)).thenReturn(historialRedencionEntityList);

        List<DetalleHistorialDTO> detalleConsulta = historialLogica.obtenerHistorial(historialDTO);
        assertEquals(historialPruebaEsperado, detalleConsulta);
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