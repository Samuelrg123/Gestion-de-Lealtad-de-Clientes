package com.example.lealtad.logica;

import com.example.lealtad.bd.entidad.Cliente;
import com.example.lealtad.bd.entidad.Transaccion;
import com.example.lealtad.bd.repository.ClienteRepository;
import com.example.lealtad.bd.repository.PuntoRepository;
import com.example.lealtad.bd.repository.TransaccionRepository;
import com.example.lealtad.controller.dto.TransaccionDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class TransaccionLogica {
    private TransaccionRepository transaccionRepository;
    private ClienteRepository clienteRepository;
    private PuntoRepository puntoRepository;
    public void guardarTransaccion(TransaccionDTO transaccionDTO){
        Transaccion transaccion = new Transaccion();
        transaccion.setCliente(clienteRepository.findById(transaccionDTO.getCliente()).get());
        transaccion.setFecha_transaccion(LocalDate.now());
        transaccion.setMonto(transaccionDTO.getMonto());
        double puntosGenerados = calcularPuntosGenerados(transaccionDTO.getMonto());
        transaccion.setPuntos_generados(puntosGenerados);
        transaccion.setFecha_creacion(LocalDate.now());
        transaccion.setFecha_modificacion(LocalDate.now());

        transaccionRepository.save(transaccion);

        actualizarPuntosCliente(transaccionDTO.getCliente(), puntosGenerados);
    }

    private void actualizarPuntosCliente(int cedulaCliente, double puntosGenerados) {
        double puntosActuales = puntoRepository.findPuntosAcumuladosByCliente(cedulaCliente);
        double puntosAcumulados = puntosGenerados + puntosActuales;
        puntoRepository.updatePuntosetPuntosAcumulados(cedulaCliente,puntosAcumulados);
    }

    private double calcularPuntosGenerados(int monto) {
        double PuntosPorDolar = 0.5;
        return (monto*PuntosPorDolar);
    }
}

