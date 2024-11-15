package com.TTPS2024.buffet.service.carta.producto;

import com.TTPS2024.buffet.controller.dto.ComidaDTO;
import com.TTPS2024.buffet.controller.request.carta.producto.ComidaRequest;
import com.TTPS2024.buffet.dao.carta.producto.ComidaDAO;
import com.TTPS2024.buffet.helper.transformer.ComidaTransformer;
import com.TTPS2024.buffet.model.carta.producto.Comida;
import com.TTPS2024.buffet.model.carta.producto.Menu;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComidaService extends ProductoComercializableService<Comida,ComidaDAO, ComidaRequest, ComidaDTO, ComidaTransformer> {
    @Autowired
    public ComidaService(ComidaDAO comidaDAO, ComidaTransformer comidaTransformer) {
        super(comidaDAO, comidaTransformer);
    }


    @Transactional
    public ComidaDTO updateComidaMenuRelation(Menu menu, Long comidaId){
        Comida comida = this.getProductByIdInternal(comidaId);
        comida.setComidaInMenu(menu);
        Comida result = this.dao.saveAndFlush(comida);
        return this.transformer.toDTO(result);
    }

    @Override
    protected void sanitizeRequestSpecificFields(ComidaRequest comidaRequest) {
        if(comidaRequest.getTipoComida() == null){
            throw new IllegalArgumentException("Tipo de comida no puede ser nulo o no existir");
        }
    }

    @Override
    protected void setUpdateSpecificFields(Comida originalProduct, ComidaRequest request) {
        originalProduct.setTipoComida(request.getTipoComida());
    }

    @Override
    protected Comida createProductoComercializable(ComidaRequest request) {
        return new Comida(request.getNombre(), request.getTipoComida(), request.getPrecio(), request.getImagen());
    }

    @Override
    protected void updateComidasEnMenuRelation(Comida originalProduct, Comida result) {
        // no aplica a este caso, solo para menues
    }
    @Override
    protected void updateSpecificRelations(Comida originalProduc, Comida updatedProduct, ComidaRequest request) {
        // no aplica a este caso, solo para menues
    }



}

