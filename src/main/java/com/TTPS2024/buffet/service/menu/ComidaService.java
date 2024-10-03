package com.TTPS2024.buffet.service.menu;

import com.TTPS2024.buffet.dao.menu.ComidaDAO;
import com.TTPS2024.buffet.model.carta.producto.Comida;
import com.TTPS2024.buffet.model.carta.producto.EtiquetaComida;
import com.TTPS2024.buffet.model.carta.producto.TipoComida;
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

    @Transactional
    public Comida saveComida(String nombre, TipoComida tipoComida, Double precio, List<EtiquetaComida> etiquetas) {
        Comida comida = new Comida(nombre, tipoComida, precio, etiquetas);
        return comidaDAO.save(comida);
    }

    public void deleteComida(Long id) {
        comidaDAO.deleteById(id);
    }


}
