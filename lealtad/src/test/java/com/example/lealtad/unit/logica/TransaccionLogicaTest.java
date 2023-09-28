package com.example.lealtad.unit.logica;

import com.example.lealtad.bd.entidad.Cliente;
import com.example.lealtad.bd.entidad.Transaccion;
import com.example.lealtad.bd.repository.ClienteRepository;
import com.example.lealtad.bd.repository.TransaccionRepository;
import com.example.lealtad.controller.dto.PuntoDTO;
import com.example.lealtad.controller.dto.TransaccionDTO;
import com.example.lealtad.logica.PuntoLogica;
import com.example.lealtad.logica.TransaccionLogica;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransaccionLogicaTest {
    @Mock
    private TransaccionRepository transaccionRepository;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private PuntoLogica puntoLogica;
    @InjectMocks
    TransaccionLogica transaccionLogica;

    @Test
    void Dado_transaccion_cliente_1_monto_2_Cuando_guardarTransaccion_Entonces_puntosacumulados_1_guardaTransaccion() {
        Cliente cliente = new Cliente();
        cliente.setCedula(1);
        cliente.setNombre("Prueba");
        cliente.setApellido("Unitaria");
        cliente.setCorreo("pruebas@pruebas.com");
        cliente.setTelefono(1234);
        cliente.setFechaCreacion(LocalDate.now());
        cliente.setFechaModificacion(LocalDate.now());

        TransaccionDTO transaccionDTO = new TransaccionDTO();
        transaccionDTO.setCliente(1);
        transaccionDTO.setMonto(2);

        Transaccion transaccion = new Transaccion();
        transaccion.setCliente(cliente);
        transaccion.setFechaTransaccion(LocalDate.now());
        transaccion.setMonto(transaccionDTO.getMonto());
        double puntosGenerados = 1;
        transaccion.setPuntosGenerados(puntosGenerados);
        transaccion.setFechaCreacion(LocalDate.now());
        transaccion.setFechaModificacion(LocalDate.now());

        when(clienteRepository.findById(transaccionDTO.getCliente())).thenReturn(Optional.of(cliente));
        transaccionLogica.guardarTransaccion(transaccionDTO);
        Mockito.verify(transaccionRepository).save(transaccion);
    }

    @Test
    void Dado_transaccion_cliente_1_monto_2_Cuando_actualicePuntos_Entonces_puntosActuales_0_puntosAcumulados_1_y_actualizarPuntos() {
        TransaccionDTO transaccionDTO = new TransaccionDTO();
        transaccionDTO.setCliente(1);
        transaccionDTO.setMonto(2);

        PuntoDTO puntoDTO = new PuntoDTO(transaccionDTO.getCliente());

        when(puntoLogica.obtenerPuntosActuales(puntoDTO)).thenReturn(0.0);
        transaccionLogica.actualizarPuntosCliente(transaccionDTO);
        puntoDTO.setPuntosAcumulados(1);
        Mockito.verify(puntoLogica).actualizarPuntos(puntoDTO);

    }
}