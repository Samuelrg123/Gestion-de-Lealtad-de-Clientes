package com.example.lealtad.logica;

import com.example.lealtad.bd.entidad.Recompensa;
import com.example.lealtad.bd.repository.RecompensaRepository;
import com.example.lealtad.controller.dto.HistorialDTO;
import com.example.lealtad.controller.dto.PuntoDTO;
import com.example.lealtad.controller.dto.RecompensaDTO;
import com.example.lealtad.controller.dto.RespuestaDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecompensaLogica {
    private RecompensaRepository recompensaRepository;
    private PuntoLogica puntoLogica;
    private HistorialLogica historialLogica;
    public List<Recompensa> buscarRecompensasPorCliente(RecompensaDTO recompensaDTO) {
        double puntosCliente = puntoLogica.obtenerPuntosActuales(new PuntoDTO(recompensaDTO.getCedula()));
        return recompensaRepository.findAllInfoRecompensasByPuntosCliente(puntosCliente);
    }

    public List<Recompensa> buscarRecompensas(){
       return recompensaRepository.findAll();
    }

    public RespuestaDTO redimirRecompensa(RecompensaDTO recompensaDTO){
        double puntosCliente = puntoLogica.obtenerPuntosActuales(new PuntoDTO(recompensaDTO.getCedula()));
        Recompensa recompensa = recompensaRepository.findById(recompensaDTO.getId_recompensa()).get();
        int puntosRecompensa = recompensa.getPuntos_necesarios();
        if (puntosRecompensa<=puntosCliente){
            historialLogica.registrarRecompensaRedimida(new HistorialDTO(recompensaDTO.getCedula(),recompensaDTO.getId_recompensa()));
            double puntosActualizados = puntosCliente-puntosRecompensa;
            puntoLogica.actualizarPuntos(new PuntoDTO(recompensaDTO.getCedula(),puntosActualizados));
            return new RespuestaDTO("Recompensa redimida y registrada con exito. Se han descontado los puntos al cliente");
        }else{
            return new RespuestaDTO("No cumple con la cantidad de puntos para redimir esta recompensa. revise http://localhost:8080/recomepensas/"+recompensaDTO.getCedula()+" para conocer las recompensas a las que puede acceder actualmente");
        }
    }
}
