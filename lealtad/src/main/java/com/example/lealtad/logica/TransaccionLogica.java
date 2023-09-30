package com.example.lealtad.logica;

import com.example.lealtad.bd.entidad.Transaccion;
import com.example.lealtad.bd.repository.ClienteRepository;
import com.example.lealtad.bd.repository.TransaccionRepository;
import com.example.lealtad.controller.dto.PuntoDTO;
import com.example.lealtad.controller.dto.TransaccionDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class TransaccionLogica {
    private TransaccionRepository transaccionRepository;
    private ClienteRepository clienteRepository;
    private PuntoLogica puntoLogica;

    public void guardarTransaccion(TransaccionDTO transaccionDTO) {
        Transaccion transaccion = new Transaccion();
        transaccion.setCliente(clienteRepository.findById(transaccionDTO.getCliente()).get());
        transaccion.setFechaTransaccion(LocalDate.now());
        transaccion.setMonto(transaccionDTO.getMonto());
        double puntosGenerados = calcularPuntosGenerados(transaccionDTO.getMonto());
        transaccion.setPuntosGenerados(puntosGenerados);
        transaccion.setFechaCreacion(LocalDate.now());
        transaccion.setFechaModificacion(LocalDate.now());

        transaccionRepository.save(transaccion);
    }

    public void actualizarPuntosCliente(TransaccionDTO transaccionDTO) {
        PuntoDTO puntoDTO = new PuntoDTO();
        puntoDTO.setCliente(transaccionDTO.getCliente());
        double puntosActuales = puntoLogica.obtenerPuntosActuales(puntoDTO);
        double puntosGenerados = calcularPuntosGenerados(transaccionDTO.getMonto());
        double puntosAcumulados = puntosGenerados + puntosActuales;
        puntoDTO.setPuntosAcumulados(puntosAcumulados);
        puntoLogica.actualizarPuntos(puntoDTO);
    }

    private double calcularPuntosGenerados(int monto) {
        double PuntosPorDolar = 0.5;
        return (monto * PuntosPorDolar);
    }
}

