package com.example.lealtad.unit.logica;

import com.example.lealtad.bd.entidad.Recompensa;
import com.example.lealtad.bd.repository.RecompensaRepository;
import com.example.lealtad.controller.dto.*;
import com.example.lealtad.logica.HistorialLogica;
import com.example.lealtad.logica.PuntoLogica;
import com.example.lealtad.logica.RecompensaLogica;
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
class RecompensaLogicaTest {
    @Mock
    private RecompensaRepository recompensaRepository;
    @Mock
    private PuntoLogica puntoLogica;
    @Mock
    private HistorialLogica historialLogica;
    @InjectMocks
    RecompensaLogica recompensaLogica;

    @Test
    void Dado_recompensa_Cuando_guarde_Entonces_guardaRecompensa() {
        RecompensaEntidadDTO recompensaEntidadDTO = new RecompensaEntidadDTO(1, "Tarjeta $10", "Recibe una tarjeta de regalo de $10 para gastar en nuestra tienda", 20);
        Recompensa recompensa = new Recompensa();
        recompensa.setIdRecompensa(recompensaEntidadDTO.getIdRecompensa());
        recompensa.setNombre(recompensaEntidadDTO.getNombre());
        recompensa.setDescripcion(recompensaEntidadDTO.getDescripcion());
        recompensa.setPuntosNecesarios(recompensaEntidadDTO.getPuntosNecesarios());
        recompensa.setFechaCreacion(LocalDate.now());
        recompensa.setFechaModificacion(LocalDate.now());

        recompensaLogica.guardarRecompensa(recompensaEntidadDTO);

        Mockito.verify(recompensaRepository).save(recompensa);
    }

    @Test
    void Dado_cliente_puntosCliente3_Cuando_buscarRecompensasPorCliente_Entonces_devuelve_detalleRecompensaDTO_recompensaConPuntosNecesariosMenoresoIguales_a_3() {
        RecompensaDTO recompensaDTO = new RecompensaDTO();
        recompensaDTO.setCedula(1);

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

        List<Recompensa> recompensaEntityList = new ArrayList<>();
        recompensaEntityList.add(recompensa_1);

        DetalleRecompensaDTO detalleRecompensaDTO_1 = new DetalleRecompensaDTO(recompensa_1.getIdRecompensa(),recompensa_1.getNombre(), recompensa_1.getDescripcion(), recompensa_1.getPuntosNecesarios());
        List<DetalleRecompensaDTO> detalleRecompensaDTOListaEsperada = new ArrayList<>();
        detalleRecompensaDTOListaEsperada.add(detalleRecompensaDTO_1);

        double puntosCliente = 3.0;
        PuntoDTO puntoDTO = new PuntoDTO();
        puntoDTO.setCliente(recompensaDTO.getCedula());

        when(puntoLogica.obtenerPuntosActuales(puntoDTO)).thenReturn(puntosCliente);
        when(recompensaRepository.findAllInfoRecompensasByPuntosCliente(puntosCliente)).thenReturn(recompensaEntityList);

        List<DetalleRecompensaDTO> detalleRecompensaDTOListaActual = recompensaLogica.buscarRecompensasPorCliente(recompensaDTO);
        assertEquals(detalleRecompensaDTOListaEsperada, detalleRecompensaDTOListaActual);
    }

    @Test
    void Dado_RecompensaFindAll_Cuando_buscarRecompensas_Entonces_obtener_lista_DetalleReompensaDTO() {
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

        List<Recompensa> recompensaEntityList = new ArrayList<>();
        recompensaEntityList.add(recompensa_1);
        recompensaEntityList.add(recompensa_2);

        DetalleRecompensaDTO detalleRecompensaDTO_1 = new DetalleRecompensaDTO(recompensa_1.getIdRecompensa(),recompensa_1.getNombre(), recompensa_1.getDescripcion(), recompensa_1.getPuntosNecesarios());
        DetalleRecompensaDTO detalleRecompensaDTO_2 = new DetalleRecompensaDTO(recompensa_2.getIdRecompensa(),recompensa_2.getNombre(), recompensa_2.getDescripcion(), recompensa_2.getPuntosNecesarios());

        List<DetalleRecompensaDTO> detalleRecompensaDTOListaEsperada = new ArrayList<>();
        detalleRecompensaDTOListaEsperada.add(detalleRecompensaDTO_1);
        detalleRecompensaDTOListaEsperada.add(detalleRecompensaDTO_2);

        when(recompensaRepository.findAll()).thenReturn(recompensaEntityList);

        List<DetalleRecompensaDTO> detalleRecompensaDTOListaActual = recompensaLogica.buscarRecompensas();
        assertEquals(detalleRecompensaDTOListaEsperada, detalleRecompensaDTOListaActual);
    }

    @Test
    void Dado_cliente_sin_puntos_necesarios_Cuando_redimirRecompensa_Entonces_respuestDTO_NoCumplePuntos() {
        RecompensaDTO recompensaDTO = new RecompensaDTO(1, 1);

        Recompensa recompensa = new Recompensa();
        recompensa.setIdRecompensa(1);
        recompensa.setNombre("recompensaPrueba");
        recompensa.setDescripcion("Esto es una recompensa de prueba");
        recompensa.setPuntosNecesarios(2);
        recompensa.setFechaCreacion(LocalDate.now());
        recompensa.setFechaModificacion(LocalDate.now());

        PuntoDTO puntoDTO = new PuntoDTO();
        puntoDTO.setCliente(1);

        when(puntoLogica.obtenerPuntosActuales(puntoDTO)).thenReturn(0.0);
        when(recompensaRepository.findById(1)).thenReturn(Optional.of(recompensa));
        RespuestaDTO respuestaDTOEsperada = new RespuestaDTO("No cumple con la cantidad de puntos para redimir esta recompensa. revise http://localhost:8080/recompensas/" + recompensaDTO.getCedula() + " para conocer las recompensas a las que puede acceder actualmente");

        RespuestaDTO respuestaActual = recompensaLogica.redimirRecompensa(recompensaDTO);
        assertEquals(respuestaDTOEsperada, respuestaActual);
    }

    @Test
    void Dado_cliente_con_puntos_necesarios_Cuando_redimirRecompensa_Entonces_registrarRecompensaRedimida_actualizarPuntos_y_respuestDTO_RecompensaRedimida() {
        RecompensaDTO recompensaDTO = new RecompensaDTO(1, 1);

        Recompensa recompensa = new Recompensa();
        recompensa.setIdRecompensa(1);
        recompensa.setNombre("recompensaPrueba");
        recompensa.setDescripcion("Esto es una recompensa de prueba");
        recompensa.setPuntosNecesarios(2);
        recompensa.setFechaCreacion(LocalDate.now());
        recompensa.setFechaModificacion(LocalDate.now());

        PuntoDTO puntoDTO = new PuntoDTO();
        puntoDTO.setCliente(1);

        when(puntoLogica.obtenerPuntosActuales(puntoDTO)).thenReturn(3.0);
        double puntosActulizados = 1;
        when(recompensaRepository.findById(1)).thenReturn(Optional.of(recompensa));
        RespuestaDTO respuestaDTOEsperada = new RespuestaDTO("Recompensa redimida y registrada con exito. Se han descontado los puntos al cliente");

        RespuestaDTO respuestaActual = recompensaLogica.redimirRecompensa(recompensaDTO);
        Mockito.verify(historialLogica).registrarRecompensaRedimida(new HistorialDTO(recompensaDTO.getCedula(), recompensaDTO.getIdRecompensa()));
        Mockito.verify(puntoLogica).actualizarPuntos(new PuntoDTO(recompensaDTO.getCedula(), puntosActulizados));
        assertEquals(respuestaDTOEsperada, respuestaActual);
    }
}