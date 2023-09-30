package com.example.lealtad.integration.controller;

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
    void Dado_clienteDTO_Cuando_PostRequest_cliente_guardar_Entonces_devuelve_respuestaDTO() {
        ClienteDTO dto = new ClienteDTO(1, "Prueba", "Integracion", "pruebas@pruebas.com", 1234);

        ResponseEntity<RespuestaDTO> respuesta = restTemplate.postForEntity("/cliente/guardar", dto, RespuestaDTO.class);
        assertEquals("Cliente registrado correctamente", respuesta.getBody().getMensaje());
    }
}