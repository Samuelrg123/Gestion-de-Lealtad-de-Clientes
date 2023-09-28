package com.example.lealtad.unit.logica;

import com.example.lealtad.bd.entidad.Cliente;
import com.example.lealtad.bd.entidad.Punto;
import com.example.lealtad.bd.repository.ClienteRepository;
import com.example.lealtad.bd.repository.PuntoRepository;
import com.example.lealtad.controller.dto.ClienteDTO;
import com.example.lealtad.logica.ClienteLogica;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class ClienteLogicaTest {
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private PuntoRepository puntoRepository;
    @InjectMocks
    ClienteLogica clienteLogica;

    @Test
    void Dado_cliente_Cuando_guarde_Entonces_guardaClienteyPuntos() {
        ClienteDTO dto = new ClienteDTO(10, "Prueba", "Unitaria", "pruebas@pruebas.com", 1234);
        Cliente cliente = new Cliente();
        cliente.setCedula(dto.getCedula());
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setCorreo(dto.getCorreo());
        cliente.setTelefono(dto.getTelefono());
        cliente.setFechaCreacion(LocalDate.now());
        cliente.setFechaModificacion(LocalDate.now());

        Punto punto = new Punto();
        punto.setCliente(cliente);
        punto.setPuntosAcumulados(0);
        punto.setFechaCreacion(LocalDate.now());
        punto.setFechaAcumulacion(LocalDate.now());

        clienteLogica.guardarCliente(dto);

        Mockito.verify(clienteRepository).save(cliente);
        Mockito.verify(puntoRepository).save(punto);

    }
}