package com.example.lealtad.controller;

import com.example.lealtad.controller.dto.ClienteDTO;
import com.example.lealtad.controller.dto.RespuestaDTO;
import com.example.lealtad.logica.ClienteLogica;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControladorCliente {

    private ClienteLogica clienteLogica;

    public ControladorCliente(ClienteLogica clienteLogica) {
        this.clienteLogica = clienteLogica;
    }

    @PostMapping(path = "/cliente/guardar")
    public RespuestaDTO guardarCliente(@RequestBody ClienteDTO clienteDTO) {
        clienteLogica.guardarCliente(clienteDTO);
        return new RespuestaDTO("Cliente registrado correctamente");
    }
}
