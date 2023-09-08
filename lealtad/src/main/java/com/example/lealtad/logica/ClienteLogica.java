package com.example.lealtad.logica;

import com.example.lealtad.bd.entidad.Cliente;
import com.example.lealtad.bd.repository.ClienteRepository;
import com.example.lealtad.controller.dto.ClienteDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ClienteLogica {
    private ClienteRepository clienteRepository;
    public void guardarCliente(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setCedula(clienteDTO.getCedula());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setFecha_creacion(LocalDate.now());
        cliente.setFecha_modificacion(LocalDate.now());

        clienteRepository.save(cliente);
    }
}
