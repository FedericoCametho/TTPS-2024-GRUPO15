package com.TTPS2024.buffet.model.request;

import com.TTPS2024.buffet.model.carta.producto.EtiquetaComida;
import com.TTPS2024.buffet.model.carta.producto.TipoComida;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class ComidaRequest {
    private String nombre;
    private TipoComida tipoComida;
    private Double precio;
    private List<EtiquetaComida> etiquetas;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoComida getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(TipoComida tipoComida) {
        this.tipoComida = tipoComida;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public List<EtiquetaComida> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<EtiquetaComida> etiquetas) {
        this.etiquetas = etiquetas;
    }
}
