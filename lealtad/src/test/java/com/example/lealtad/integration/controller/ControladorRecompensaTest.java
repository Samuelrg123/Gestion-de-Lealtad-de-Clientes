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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
class ControladorRecompensaTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void Dado_recompensaEntidadDTO_recompensaId_1_Cuando_PostRequest_recompensa_guardar_Entonces_devuelve_respuestaDTO() {
        RecompensaEntidadDTO recompensaEntidadDTO = new RecompensaEntidadDTO(1, "Tarjeta $10", "Recibe una tarjeta de regalo de $10 para gastar en nuestra tienda", 1);
        ResponseEntity<RespuestaDTO> respuesta = restTemplate.postForEntity("/recompensa/guardar", recompensaEntidadDTO, RespuestaDTO.class);
        assertEquals("Recompensa registrado correctamente", respuesta.getBody().getMensaje());
    }

    @Test
    void Dado_GetRequest_Cuando_recompensas_Entonces_recibe_detalleRecompensaDTO_recompensas() {
        RecompensaEntidadDTO recompensaEntidadDTO = new RecompensaEntidadDTO(1, "Tarjeta $10", "Recibe una tarjeta de regalo de $10 para gastar en nuestra tienda", 1);
        RecompensaEntidadDTO recompensaEntidadDTO_2 = new RecompensaEntidadDTO(2, "Tarjeta $20", "Recibe una tarjeta de regalo de $20 para gastar en nuestra tienda", 2);
        RecompensaEntidadDTO recompensaEntidadDTO_3 = new RecompensaEntidadDTO(3, "Tarjeta $20", "Recibe una tarjeta de regalo de $20 para gastar en nuestra tienda", 1);

        restTemplate.postForEntity("/recompensa/guardar", recompensaEntidadDTO_2, RespuestaDTO.class);

        DetalleRecompensaDTO detalleRecompensaDTO = new DetalleRecompensaDTO();
        detalleRecompensaDTO.setId(recompensaEntidadDTO.getIdRecompensa());
        detalleRecompensaDTO.setNombre(recompensaEntidadDTO.getNombre());
        detalleRecompensaDTO.setDescripcion(recompensaEntidadDTO.getDescripcion());
        detalleRecompensaDTO.setPuntosNecesarios(recompensaEntidadDTO.getPuntosNecesarios());

        DetalleRecompensaDTO detalleRecompensaDTO_2 = new DetalleRecompensaDTO();
        detalleRecompensaDTO_2.setId(recompensaEntidadDTO_2.getIdRecompensa());
        detalleRecompensaDTO_2.setNombre(recompensaEntidadDTO_2.getNombre());
        detalleRecompensaDTO_2.setDescripcion(recompensaEntidadDTO_2.getDescripcion());
        detalleRecompensaDTO_2.setPuntosNecesarios(recompensaEntidadDTO_2.getPuntosNecesarios());

        DetalleRecompensaDTO detalleRecompensaDTO_3 = new DetalleRecompensaDTO();
        detalleRecompensaDTO_3.setId(recompensaEntidadDTO_3.getIdRecompensa());
        detalleRecompensaDTO_3.setNombre(recompensaEntidadDTO_3.getNombre());
        detalleRecompensaDTO_3.setDescripcion(recompensaEntidadDTO_3.getDescripcion());
        detalleRecompensaDTO_3.setPuntosNecesarios(recompensaEntidadDTO_3.getPuntosNecesarios());


        DetalleRecompensaDTO[] recompensasEsperadas = {detalleRecompensaDTO, detalleRecompensaDTO_2,detalleRecompensaDTO_3};

        ResponseEntity<DetalleRecompensaDTO[]> detalleHistorialDTOResponse = restTemplate.getForEntity("/recompensas", DetalleRecompensaDTO[].class);
        DetalleRecompensaDTO[] recompensas = detalleHistorialDTOResponse.getBody();
        assertArrayEquals(recompensasEsperadas, recompensas);
    }

    @Test
    void Dado_cedulaCliente_Cuando_GetRequest_recompensas_cedula_Entonces_devuelve_detalleRecompensaDTO() {
        RecompensaEntidadDTO recompensaEntidadDTO_1 = new RecompensaEntidadDTO(1, "Tarjeta $10", "Recibe una tarjeta de regalo de $10 para gastar en nuestra tienda", 1);

        DetalleRecompensaDTO detalleRecompensaDTO = new DetalleRecompensaDTO();
        detalleRecompensaDTO.setId(recompensaEntidadDTO_1.getIdRecompensa());
        detalleRecompensaDTO.setNombre(recompensaEntidadDTO_1.getNombre());
        detalleRecompensaDTO.setDescripcion(recompensaEntidadDTO_1.getDescripcion());
        detalleRecompensaDTO.setPuntosNecesarios(recompensaEntidadDTO_1.getPuntosNecesarios());
        DetalleRecompensaDTO[] recompensasEsperadas = {detalleRecompensaDTO};

        ClienteDTO dto = new ClienteDTO(4, "Prueba4", "Integracion4", "pruebas@pruebas.com", 1234);
        restTemplate.postForEntity("/cliente/guardar", dto, RespuestaDTO.class);

        TransaccionDTO transaccionDTO = new TransaccionDTO();
        transaccionDTO.setCliente(dto.getCedula());
        transaccionDTO.setMonto(2);
        restTemplate.postForEntity("/transaccion/guardar", transaccionDTO, RespuestaDTO.class);

        ResponseEntity<DetalleRecompensaDTO[]> detalleHistorialDTOResponse = restTemplate.getForEntity("/recompensas/" + dto.getCedula(), DetalleRecompensaDTO[].class);
        DetalleRecompensaDTO[] recompensas = detalleHistorialDTOResponse.getBody();
        assertArrayEquals(recompensasEsperadas, recompensas);
    }

    @Test
    void Dado_recompensaDTO_IdRecompensa_3_Cuando_PostRequest_redimir_recompensa_Entonces_devuelve_respuestaDTO() {
        ClienteDTO dto = new ClienteDTO(5, "Prueba5", "Integracion5", "pruebas@pruebas.com", 1234);
        restTemplate.postForEntity("/cliente/guardar", dto, RespuestaDTO.class);

        TransaccionDTO transaccionDTO = new TransaccionDTO();
        transaccionDTO.setCliente(dto.getCedula());
        transaccionDTO.setMonto(2);
        restTemplate.postForEntity("/transaccion/guardar", transaccionDTO, RespuestaDTO.class);

        RecompensaEntidadDTO recompensaEntidadDTO_3 = new RecompensaEntidadDTO(3, "Tarjeta $20", "Recibe una tarjeta de regalo de $20 para gastar en nuestra tienda", 1);
        restTemplate.postForEntity("/recompensa/guardar", recompensaEntidadDTO_3, RespuestaDTO.class);

        RecompensaDTO recompensaDTO = new RecompensaDTO(dto.getCedula(), 3);

        ResponseEntity<RespuestaDTO> respuesta = restTemplate.postForEntity("/redimir-recompensa", recompensaDTO, RespuestaDTO.class);
        assertEquals("Recompensa redimida y registrada con exito. Se han descontado los puntos al cliente", respuesta.getBody().getMensaje());
    }
}