package com.example.lealtad.controller;

import com.example.lealtad.controller.dto.ClienteDTO;
import com.example.lealtad.controller.dto.RespuestaDTO;
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
class ControladorClienteTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    void guardarCliente() {
        ClienteDTO dto = new ClienteDTO(10, "Prueba", "Unitaria", "pruebas@pruebas.com", 1234);

        ResponseEntity<RespuestaDTO> respuesta = restTemplate.postForEntity("/cliente/guardar", dto, RespuestaDTO.class);
        assertEquals("Cliente registrado correctamente",respuesta.getBody().getMensaje());
    }
}