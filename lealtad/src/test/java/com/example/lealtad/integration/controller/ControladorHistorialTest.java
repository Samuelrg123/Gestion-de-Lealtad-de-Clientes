package com.example.lealtad.integration.controller;

import com.example.lealtad.controller.dto.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
class ControladorHistorialTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void Dado_cliente_1_redencionRecompensa_Cuando_hsitorial_redencion_Entonces_detalleHistorialDTO_1_redencionRecompensa() {
        ClienteDTO clienteDTO = new ClienteDTO(2, "Prueba2", "Integracion2", "pruebas@pruebas.com", 1234);
        restTemplate.postForEntity("/cliente/guardar", clienteDTO, RespuestaDTO.class);

        TransaccionDTO transaccionDTO = new TransaccionDTO();
        transaccionDTO.setCliente(clienteDTO.getCedula());
        transaccionDTO.setMonto(10);
        restTemplate.postForEntity("/transaccion/guardar", transaccionDTO, RespuestaDTO.class);

        RecompensaEntidadDTO recompensaEntidadDTO_1 = new RecompensaEntidadDTO(1, "Tarjeta $10", "Recibe una tarjeta de regalo de $10 para gastar en nuestra tienda", 1);
        restTemplate.postForEntity("/recompensa/guardar", recompensaEntidadDTO_1, RespuestaDTO.class);
        RecompensaEntidadDTO recompensaEntidadDTO_2 = new RecompensaEntidadDTO(2, "Tarjeta $20", "Recibe una tarjeta de regalo de $20 para gastar en nuestra tienda", 2);
        restTemplate.postForEntity("/recompensa/guardar", recompensaEntidadDTO_2, RespuestaDTO.class);

        RecompensaDTO recompensaDTO = new RecompensaDTO(clienteDTO.getCedula(), 2);
        restTemplate.postForEntity("/redimir-recompensa", recompensaDTO, RespuestaDTO.class);

        DetalleHistorialDTO detalleHistorialDTO = new DetalleHistorialDTO();
        detalleHistorialDTO.setIdRedencion(1);
        detalleHistorialDTO.setNombreCliente(clienteDTO.getNombre());
        detalleHistorialDTO.setApellidoCliente(clienteDTO.getApellido());
        detalleHistorialDTO.setRecompensaId(recompensaDTO.getIdRecompensa());
        detalleHistorialDTO.setNombreRecompensa(recompensaEntidadDTO_2.getNombre());
        detalleHistorialDTO.setDescripcionRecompensa(recompensaEntidadDTO_2.getDescripcion());
        detalleHistorialDTO.setPuntosNecesariosRecompensa(recompensaEntidadDTO_2.getPuntosNecesarios());
        detalleHistorialDTO.setFechaRedencionRecompensa(LocalDate.now());

        DetalleHistorialDTO[] recompensasEsperadas = {detalleHistorialDTO};

        ResponseEntity<DetalleHistorialDTO[]> historialRecompensasResponse = restTemplate.getForEntity("/historial-redenciones" + "?cedula=" + clienteDTO.getCedula(), DetalleHistorialDTO[].class);
        DetalleHistorialDTO[] recompensas = historialRecompensasResponse.getBody();
        assertArrayEquals(recompensasEsperadas, recompensas);
    }
}