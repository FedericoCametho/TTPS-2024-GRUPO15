package com.TTPS2024.buffet.service.menu;

import com.TTPS2024.buffet.dao.menu.ComidaDAO;
import com.TTPS2024.buffet.model.carta.producto.Comida;
import com.TTPS2024.buffet.model.request.menu.ComidaRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ComidaService {
    private ComidaDAO comidaDAO;
    @Autowired
    public ComidaService(ComidaDAO comidaDAO) {
        this.comidaDAO = comidaDAO;
    }

    public Comida getComidaById(Long id) {
        return comidaDAO.findById(id).orElse(null);
    }

    public List<Comida> getComidas() {
        return comidaDAO.findAll();
    }
    public List<Comida> getComidasByNombre(String nombre) {
        return comidaDAO.findByNombre(nombre);
    }

    @Transactional
    public Comida saveComida(ComidaRequest comidaRequest) {
        this.sanitizeComidaRequest(comidaRequest);
        Comida comida = new Comida(comidaRequest.getNombre(), comidaRequest.getTipoComida(), comidaRequest.getPrecio());
        return comidaDAO.save(comida);
    }

    public void deleteComida(Long id) {
        comidaDAO.deleteById(id);
    }

    private void sanitizeComidaRequest(ComidaRequest comidaRequest){
        if(comidaRequest.getNombre() == null || comidaRequest.getNombre().isEmpty()){
            throw new IllegalArgumentException("Nombre de comida no puede ser nulo o vacio");
        }
        if(comidaRequest.getPrecio() == null || comidaRequest.getPrecio() < 0){
            throw new IllegalArgumentException("Precio de comida no puede ser nulo o negativo");
        }
        if(comidaRequest.getTipoComida() == null){
            throw new IllegalArgumentException("Tipo de comida no puede ser nulo o no existir");
        }
    }
}
