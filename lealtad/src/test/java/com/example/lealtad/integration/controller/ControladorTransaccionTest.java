package com.example.lealtad.integration.controller;

import com.example.lealtad.controller.dto.ClienteDTO;
import com.example.lealtad.controller.dto.RespuestaDTO;
import com.example.lealtad.controller.dto.TransaccionDTO;
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
class ControladorTransaccionTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void Dado_transaccionDTO_Cuando_PostRequest_transaccion_guardar_Entonces_devuelve_respuestaDTO() {
        ClienteDTO dto = new ClienteDTO(3, "Prueba3", "Integracion3", "pruebas@pruebas.com", 1234);
        restTemplate.postForEntity("/cliente/guardar", dto, RespuestaDTO.class);

        TransaccionDTO transaccionDTO = new TransaccionDTO();
        transaccionDTO.setCliente(dto.getCedula());
        transaccionDTO.setMonto(2);

        ResponseEntity<RespuestaDTO> respuesta = restTemplate.postForEntity("/transaccion/guardar", transaccionDTO, RespuestaDTO.class);
        assertEquals("Transacción guardada exitosamente y puntos acumulados actualizados", respuesta.getBody().getMensaje());
    }
}