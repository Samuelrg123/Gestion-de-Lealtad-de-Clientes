package com.example.lealtad.controller;

import com.example.lealtad.controller.dto.RespuestaDTO;
import com.example.lealtad.controller.dto.TransaccionDTO;
import com.example.lealtad.logica.TransaccionLogica;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorTransaccion {

    private TransaccionLogica transaccionLogica;

    public ControladorTransaccion(TransaccionLogica transaccionLogica) {
        this.transaccionLogica = transaccionLogica;
    }

    @PostMapping(path = "/transaccion/guardar")
    public RespuestaDTO registrarTransaccion(@RequestBody TransaccionDTO transaccionDTO) {
        transaccionLogica.guardarTransaccion(transaccionDTO);
        transaccionLogica.actualizarPuntosCliente(transaccionDTO);
        return new RespuestaDTO("Transacción guardada exitosamente y puntos acumulados actualizados");
    }
}
